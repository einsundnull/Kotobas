package com.notorein.BasicAppII;


import static com.notorein.BasicAppII.Parameter.Parameter.advancedRepeatLogicIndex;
import static com.notorein.BasicAppII.Parameter.Parameter.playSoundWhenAnswerIsShown;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Files.FilesWriteLessons;
import com.notorein.BasicAppII.Files.FilesWriteSettings;
import com.notorein.BasicAppII.Files.PlaySound;
import com.notorein.BasicAppII.Parameter.Parameter;
import com.notorein.BasicAppII.Strings.StringsEXCEL;
import com.notorein.BasicAppII.Strings.StringsUI;

import java.util.Random;


public class AnswerLogic {


    public static int countDownTillNewCards = 0;
    public static int introduceNewCardsAfterRightAnswerCount = 35;
    public static int switchLessonCounter = 0;
    public static int switchLessonCountLimit = 155;
    public static boolean allowBtnRightAction = true;
    public static boolean setCardToTheEnd = false;
    public static boolean answerIsRight = false;
    public static boolean considerAnswer = false;
    public static boolean answerIsVisible = false;
    public static boolean questionIsReversedTemp = false;
    public static boolean advancedRepeatLogicIsActive = true;
    public static boolean alwaysShowQuestionWithAnswer = true;
    public static boolean questionIsReversed = false;
    public static boolean reversAnswerAtEnd = true;
    public static boolean reversAnswerAtCertainPoint = false;
    public static boolean reversAnswerAtRandom = false;
    public static boolean introduceNewCards = true;
    //    public static boolean answerWasShownWhenDestroy = false;
    public static boolean showHint = false;
    public static boolean allowReverseCurrentCard = false;
    public static boolean switchBetweenLessons = true;
    public static boolean useTimeToRepeat = true;
    public static boolean allowReverseNextCard = true;
    public static boolean answerHintIsVisible = false;
    //    public static int advancedRepeatLogicIndex = 4;
    public static int rightAnswerLimit = 20;
    public static int rightAnswerCounter = 0;
    static AdvancedRepeatLogic advancedRepeatLogic = AdvancedRepeatLogic.NONE;
    private static Activity activity;
    private static ConstraintLayout layout;
    private final Context context;

    TextView btnLeft;
    TextView btnRight;
    TextView hint;
    TextView questionTxtVw;
    TextView questionTxtVwII;
    TextView answerTxtVw;
    TextView hintDirection;


    public AnswerLogic(Activity activity, ConstraintLayout layout, Context context) {
        btnLeft = layout.findViewById(R.id.btnLeft);
        btnRight = layout.findViewById(R.id.btnRight);
        hint = (TextView) layout.findViewById(R.id.hint);
        this.activity = activity;
        this.layout = layout;
        this.context = context;
        hintDirection = (TextView) layout.findViewById(R.id.hintDirection);
        questionTxtVw = (TextView) layout.findViewById(R.id.questionTxtView);
        questionTxtVwII = (TextView) layout.findViewById(R.id.questionTxtVwII);
        answerTxtVw = (TextView) layout.findViewById(R.id.answerTxtVw);
    }

    public void inputRight() {
        Arrays.editedCardIndex = 0;
        if (answerIsVisible) {
            answerIsRight = true;
            answerIsVisible = false;
            btnLeft.setText(StringsUI.btnLeftText[0]);
            btnRight.setText(StringsUI.btnRightText[0]);
            hint.setText(StringsUI.hintText[0]);
            Arrays.moveCardsInArrayAfterAnswer(activity, layout, false);
            Arrays.introduceNewCards();
            advancedRepeatLogicSwitch();
            questionTxtVwII.setVisibility(View.INVISIBLE);
            Arrays.hideHintLogic(layout, false);
            answerTxtVw.setVisibility(View.INVISIBLE);
            if (playSoundWhenAnswerIsShown) {
                new PlaySound(questionTxtVw, context).playSoundOnShow();
            }
        } else {
            Arrays.setIndex = 0;
            answerIsRight = false;
            answerIsVisible = true;
            questionTxtVw.setText(StringsEXCEL.insertRegex(Arrays.answer));
            answerTxtVw.setText(StringsEXCEL.insertRegex(Arrays.question));
            btnLeft.setText(StringsUI.btnLeftText[1]);
            btnRight.setText(StringsUI.btnRightText[1]);
            hint.setText(StringsUI.hintText[1]);
            if (alwaysShowQuestionWithAnswer) {
                answerTxtVw.setVisibility(View.VISIBLE);
            }
            questionTxtVwII.setVisibility(View.INVISIBLE);
            Arrays.hideHintLogic(layout, true);
            if (playSoundWhenAnswerIsShown) {
                new PlaySound(questionTxtVw, context).playSoundOnShow();
            }
        }
        Thread thread = new Thread(() -> {
            FilesWriteLessons.writeFileWords(activity, Arrays.loadedCardsAsArray);
            Arrays.progressCounter(Arrays.loadedCardsAsArray, layout);
        });
        thread.setDaemon(true);
        thread.start();
    }


    //    public void inputRight(Activity activity, ConstraintLayout layout) {
//    public void inputRight() {
//
//        if (answerIsVisible) {
//            answerIsRight = true;
//            answerIsVisible = false;
//            btnLeft.setText(StringsUI.btnLeftText[0]);
//            btnRight.setText(StringsUI.btnRightText[0]);
//            hint.setText(StringsUI.hintText[0]);
//            Arrays.moveCardsInArrayAfterAnswer(activity, layout, false);
//            Arrays.introduceNewCards();
//            advancedRepeatLogicSwitch();
//            questionTxtVwII.setVisibility(View.INVISIBLE);
//            Arrays.hideHintLogic(layout, false);
//            answerTxtVw.setVisibility(View.INVISIBLE);
//            if (playSoundWhenAnswerIsShown) {
//                new PlaySound(questionTxtVw, context).playSoundOnShow();
//            }
//
//        } else {
//            answerIsRight = false;
//            answerIsVisible = true;
//            questionTxtVw.setText(Arrays.answer);
//            answerTxtVw.setText(Arrays.question);
//            btnLeft.setText(StringsUI.btnLeftText[1]);
//            btnRight.setText(StringsUI.btnRightText[1]);
//            hint.setText(StringsUI.hintText[1]);
//            if (alwaysShowQuestionWithAnswer) {
//                answerTxtVw.setVisibility(View.VISIBLE);
//            }
//            questionTxtVwII.setVisibility(View.INVISIBLE);
//            Arrays.hideHintLogic(layout, true);
//
//            if (playSoundWhenAnswerIsShown) {
//                new PlaySound(questionTxtVw, context).playSoundOnShow();
//            }
//        }
//
//
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                FilesWriteLessons.writeFileWords(activity);
//                Arrays.progressCounter(Arrays.loadedCardsAsArray, layout);
//
//            }
//        };
//        thread.setDaemon(true);
//        thread.start();
//
//    }

    private void advancedRepeatLogicSwitch() {
        // AI
        switch (advancedRepeatLogicIndex) {
            case 0:
                int rnd = new Random().nextInt(Parameter.reverseQuestionRandomlyRNDLimit);
                if (rnd == 4) {
                    switchAnswerToQuestion();
                    layout.findViewById(R.id.questionTxtVwII).setVisibility(View.INVISIBLE);
                }
                break;
            case 1:
                int index = questionIsReversed ? Arrays.answerCardIndex : Arrays.questionCardIndex;
                if (index == Arrays.cardlistLenght - 1) {
                    switchAnswerToQuestion();
                }
                break;
            case 2:
                rightAnswerCounter++;
                if (rightAnswerCounter == rightAnswerLimit) {
                    switchAnswerToQuestion();
                    rightAnswerCounter = 0;
                }
                StringsUI.mode = new String[]{"rnd", "end", AnswerLogic.rightAnswerCounter + "/" + AnswerLogic.rightAnswerLimit, "="};
                break;
            case 999:
                switchLessons();
                break;
        }
    }


//    private void advancedRepeatLogicSwitch() {
//        if (advancedRepeatLogicIndex == 0) {
//            int rnd = new Random().nextInt(Parameter.reverseQuestionRandomlyRNDLimit);
//            if (rnd == 4) {
//                switchAnswerToQuestion();
//                layout.findViewById(R.id.questionTxtVwII).setVisibility(View.INVISIBLE);
//                return;
//            }
//        }
//
//        if (advancedRepeatLogicIndex == 1) {
//            if (questionIsReversed) {
//                if (Arrays.answerCardIndex == Arrays.cardlistLenght - 1) {
//                    switchAnswerToQuestion();
//                    return;
//                }
//            } else {
//                if (Arrays.questionCardIndex == Arrays.cardlistLenght - 1) {
//                    switchAnswerToQuestion();
//                    return;
//                }
//            }
//        }
//
//        if (advancedRepeatLogicIndex == 2) {
//            rightAnswerCounter++;
//            if (rightAnswerCounter == rightAnswerLimit) {
//                switchAnswerToQuestion();
//                rightAnswerCounter = 0;
//
//            }
//            StringsUI.mode = new String[]{"rnd", "end", AnswerLogic.rightAnswerCounter + "/" + AnswerLogic.rightAnswerLimit, "="};
//            return;
//        }
//
//        if (advancedRepeatLogicIndex == 999) {
//            switchLessons();
//            return;
//        }
//
//
//    }


    public void inputLeft() {
        // AI
        Arrays.editedCardIndex = 0;
        answerIsRight = false;
        considerAnswer = answerIsVisible ? false : true;
        answerIsVisible = false;

        if (answerIsVisible) {
            hint.setText(StringsUI.hintText[0]);
            btnLeft.setText(StringsUI.btnLeftText[0]);
            btnRight.setText(StringsUI.btnRightText[0]);
            advancedRepeatLogicSwitch();
            Arrays.setIndex = 0;
        }

        answerTxtVw.setVisibility(View.INVISIBLE);
        Arrays.moveCardsInArrayAfterAnswer(activity, layout, false);
        switchRandom();
        Arrays.hideHintLogic(layout, true);
        writeProgressToLesson(activity);
        if (playSoundWhenAnswerIsShown) {
            new PlaySound(questionTxtVw, context).playSoundOnShow();
        }
    }

//    public void inputLeft() {
//          OPTIMIZED BY AI BELOW
//        if (answerIsVisible) {
//            answerIsRight = false;
//            answerIsVisible = false;
//            considerAnswer = false;
//            hint.setText(StringsUI.hintText[0]);
//            btnLeft.setText(StringsUI.btnLeftText[0]);
//            btnRight.setText(StringsUI.btnRightText[0]);
//            advancedRepeatLogicSwitch();
//        } else {
//            answerIsRight = false;
//            answerIsVisible = false;
//            considerAnswer = true;
//        }
//
//        answerTxtVw.setVisibility(View.INVISIBLE);
//        Arrays.moveCardsInArrayAfterAnswer(activity, layout, false);
//        switchRandom();
//        Arrays.hideHintLogic(layout, true);
//        writeProgressToLesson(activity);
//        if (playSoundWhenAnswerIsShown) {
//            new PlaySound(questionTxtVw, context).playSoundOnShow();
//        }
//    }


    public static void writeProgressToLesson(Activity activity) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                FilesWriteLessons.writeFileWords(activity, Arrays.loadedCardsAsArray);
                FilesWriteSettings.writeLessonSettings(activity);
                Arrays.progressCounter(Arrays.loadedCardsAsArray, layout);
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public void switchAnswerToQuestion() {
        // AI
        questionIsReversed = !questionIsReversed;
        int questionIndex = Arrays.questionCardIndex;
        int answerIndex = Arrays.answerCardIndex;

        if (questionIsReversed) {
            hintDirection.setText(StringsUI.direction[1]);
            int temp = questionIndex;
            questionIndex = answerIndex;
            answerIndex = temp;
        } else {
            hintDirection.setText(StringsUI.direction[0]);
        }

        Arrays.question = Arrays.loadedCardsAsArray[0][questionIndex].replace("_", " ");
        Arrays.answer = Arrays.loadedCardsAsArray[0][answerIndex].replace("_", " ");
        questionTxtVw.setText(Arrays.question);
        answerTxtVw.setText(Arrays.answer);
    }

//    public void switchAnswerToQuestion() {
//        questionIsReversed = !questionIsReversed;
//        if (!questionIsReversed) {
//            hintDirection.setText(StringsUI.direction[0]);
//            if (answerIsVisible) {
//                Arrays.question = Arrays.loadedCardsAsArray[0][Arrays.answerCardIndex].replace("_", " ");
//                Arrays.answer = Arrays.loadedCardsAsArray[0][Arrays.questionCardIndex].replace("_", " ");
//            } else {
//                Arrays.question = Arrays.loadedCardsAsArray[0][Arrays.questionCardIndex].replace("_", " ");
//                Arrays.answer = Arrays.loadedCardsAsArray[0][Arrays.answerCardIndex].replace("_", " ");
//            }
//        } else {
//            hintDirection.setText(StringsUI.direction[1]);
//            if (answerIsVisible) {
//                Arrays.question = Arrays.loadedCardsAsArray[0][Arrays.questionCardIndex].replace("_", " ");
//                Arrays.answer = Arrays.loadedCardsAsArray[0][Arrays.answerCardIndex].replace("_", " ");
//            } else {
//                Arrays.question = Arrays.loadedCardsAsArray[0][Arrays.answerCardIndex].replace("_", " ");
//                Arrays.answer = Arrays.loadedCardsAsArray[0][Arrays.questionCardIndex].replace("_", " ");
//            }
//        }
//        questionTxtVw.setText(Arrays.question);
//        answerTxtVw.setText(Arrays.answer);
//    }

    //    public void switchRandom() {
//        if (4 == new Random().nextInt(5)) {
//            switchAnswerToQuestion();
//        }
//    }
    public void switchRandom() {
        int index = questionIsReversed ? Arrays.answerCardIndex : Arrays.questionCardIndex;
        if (index == Arrays.cardlistLenght - 1) {
            switchAnswerToQuestion();
        }
    }

    //
//    public void switchEnd() {
//        if (questionIsReversed) {
//            if (Arrays.answerCardIndex == Arrays.cardlistLenght - 1) {
//                switchAnswerToQuestion();
//            }
//        } else {
//            if (Arrays.questionCardIndex == Arrays.cardlistLenght - 1) {
//                switchAnswerToQuestion();
//            }
//
//        }
//    }
//
    public static void switchLessons() {
        switchLessonCounter++;
        if (switchLessonCounter >= switchLessonCountLimit && switchBetweenLessons) {
            switchLessonCounter = 0;
        }
    }


}
