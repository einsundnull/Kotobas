package com.notorein.BasicAppII;

import static com.notorein.BasicAppII.Arrays.answer;
import static com.notorein.BasicAppII.Arrays.answerCardHintIndexMarker;
import static com.notorein.BasicAppII.Arrays.answerCardIndex;
import static com.notorein.BasicAppII.Arrays.answerHint;
import static com.notorein.BasicAppII.Arrays.cardlistLenght;
import static com.notorein.BasicAppII.Arrays.getAllLessonsInCustomFolder;
import static com.notorein.BasicAppII.Arrays.lessonIndex;
import static com.notorein.BasicAppII.Arrays.lessonNames;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsArray;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsList;
import static com.notorein.BasicAppII.Arrays.question;
import static com.notorein.BasicAppII.Arrays.questionCardHintIndexMarker;
import static com.notorein.BasicAppII.Arrays.questionCardIndex;
import static com.notorein.BasicAppII.Arrays.questionHint;
import static com.notorein.BasicAppII.Arrays.showHintIndexMarker;
import static com.notorein.BasicAppII.Parameter.Parameter.answerTextSize;
import static com.notorein.BasicAppII.Parameter.Parameter.bigLetters;
import static com.notorein.BasicAppII.Parameter.Parameter.buttonLayoutWasMovedUpwards;
import static com.notorein.BasicAppII.Parameter.Parameter.buttonLayoutY;
import static com.notorein.BasicAppII.Parameter.Parameter.buttonLayoutYCounter;
import static com.notorein.BasicAppII.Parameter.Parameter.displayHeight;
import static com.notorein.BasicAppII.Parameter.Parameter.displayWidth;
import static com.notorein.BasicAppII.Parameter.Parameter.hideHint;
import static com.notorein.BasicAppII.Parameter.Parameter.mode;
import static com.notorein.BasicAppII.Parameter.Parameter.moveButtons;
import static com.notorein.BasicAppII.Parameter.Parameter.newCardTransitionDuration;
import static com.notorein.BasicAppII.Parameter.Parameter.newCardTransitionDurationHint;
import static com.notorein.BasicAppII.Parameter.Parameter.nightMode;
import static com.notorein.BasicAppII.Parameter.Parameter.orientationI;
import static com.notorein.BasicAppII.Parameter.Parameter.orientationTemporaryII;
import static com.notorein.BasicAppII.Parameter.Parameter.questionTextSize;
import static com.notorein.BasicAppII.Parameter.Parameter.questionTextSizeII;
import static com.notorein.BasicAppII.Parameter.Parameter.saveBatteryMode;
import static com.notorein.BasicAppII.Parameter.Parameter.settingsWereUsed;
import static com.notorein.BasicAppII.Parameter.Parameter.tempButtonLayoutY;
import static com.notorein.BasicAppII.Parameter.Parameter.useFadeAnimationInQuestionLayout;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.cellIndex;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.columnIndex;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.destinationDirectory;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.fileExtension;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.sheetIndex;
import static com.notorein.BasicAppII.Strings.StringsAdaptAndCut.adaptTextViewTextSize;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewbinding.BuildConfig;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Dialogs.ViewMenu;
import com.notorein.BasicAppII.Dialogs.ViewMenuAd;
import com.notorein.BasicAppII.Files.CreateDirectory;
import com.notorein.BasicAppII.Files.FilesReadLesson;
import com.notorein.BasicAppII.Files.FilesReadSettings;
import com.notorein.BasicAppII.Files.FilesWriteSettings;
import com.notorein.BasicAppII.Files.PlaySound;
import com.notorein.BasicAppII.POI.POIReadEXCELFile;
import com.notorein.BasicAppII.POI.POIWriteNewEXCELFile;
import com.notorein.BasicAppII.Parameter.Parameter;
import com.notorein.BasicAppII.Parameter.ParameterEXCEL;
import com.notorein.BasicAppII.Strings.StringsEXCEL;
import com.notorein.BasicAppII.Strings.StringsUI;

import java.io.File;
import java.util.Random;


public class ActivityMain extends AppCompatActivity implements View.OnClickListener {


    public static Runnable fadeAction;
    public static Runnable fadeActionHint;
    private Animation mLoadAnimation;
    private Animation mLoadAnimationHint;
    private ValueAnimator colorAnimation;
    private static MediaPlayer mediaPlayer;
    public static ConstraintLayout questioningLayout;
    //    public static Rect bounds;
    private MyCanvas canvas;

    public LinearLayout btnContainer;
    public TextView questionTxtVw;
    public TextView questionTxtVwII;
    public TextView answerTxtVw;
    public TextView hint;
    public TextView hintDirection;
    public TextView hintMode;
    public TextView menuIcon;
    private TextView menuIconEditCard;
    private TextView menuIconAdNewCard;
    public TextView btnLeft;
    public TextView btnRight;
    public TextView btnSetToEnd;
    //    public TextView progressStatus;
    private AnswerLogic answerLogic;
    CreateDirectory createDirectory;
    private ToneGenerator toneGenerator;
    // THis is just a playaround value. It does nothing for the app. Only learning purposes
    private int noteCounter;
    public static Context context;
    private int REQUEST_RECORD_AUDIO_PERMISSION = 200;


//    private PlaySound playSound;


    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            // If the permission request is cancelled, the result arrays are empty
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted, start recording audio
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "Microphone permission is required to record audio", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isMicrophonePresent() {
        PackageManager pm = getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            return true;
        } else {
            return false;
        }
    }

    private void requestMicrophonePermission() {
        // Check if the microphone permission has been granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission has not been granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO_PERMISSION);
        }
    }


    public void startApp() {
//         look at the sorted array . It sorts it wrongly  like 2 1 4 3 6 5
        setContentView(R.layout.layout_questioning);
        questioningLayout = findViewById(R.id.questioning_layout);
        createDirectory = new CreateDirectory(this, this, ParameterEXCEL.folderName);
        createDirectory.createDirectory();
        context = this;
        getDisplayMetrics();
        FilesWriteSettings.createSettingsFiles(this, this);
        getAllLessonsInCustomFolder(this);
        lessonIndex = 0;
        FilesReadSettings.scanStartUpSettingsFile(this, this);
        FilesReadSettings.scanLessonSettingsFile(this);
        // This ensures that the app does not crash in case of an empty custom folder.
//        Toast.makeText(this,""+lessonIndex,Toast.LENGTH_LONG).show();
        getLessonsPorcess();
        getViews();
        getTextViewsTextSize();
        setFadeAnimation();
        setFadeAnimationHint();
        setTextViewOnCLick();
        answerLogic = new AnswerLogic(this, questioningLayout, this);
//        playSound =  new PlaySound(questionTxtVw, this);
        answerLogic.answerIsVisible = false;
        Arrays.firstCardInRow = loadedCardsAsArray[0];
        Arrays.secondCardInRow = loadedCardsAsArray[1];
        Arrays.progressCounter(loadedCardsAsArray, questioningLayout);
        setTextToUiElements();
        setUiElementsVisibility(View.VISIBLE);
        settingsWereUsed = false;
        setBigLetters();
//        adaptDisplayToModesOnAppAlreadyRunning();
        switchDayAndNight();
        setOrientationListenerI();
//        setMediaPlayer(this);
        switchAdvancedRepeatLogic();
//        this.findViewById(R.id.btnContainer).setY(pxFromDp(this, -buttonLayoutY * buttonLayoutYCounter));
        setOrientationListenerII();
//        createMelody();
        AnswerLogic.writeProgressToLesson(this);
        if (useFadeAnimationInQuestionLayout) {
            setFadeInAnimation(this, questioningLayout, null, android.R.anim.fade_in, 3000, 0);
        }
        useFadeAnimationInQuestionLayout = true;
        if (Parameter.deletedLesson == true) {
            Parameter.deletedLesson = false;
            Intent intent = new Intent(this, ActivityLessons.class);
            startActivity(intent);
        }
//        Intent intent = new Intent(this, ActivityQuickEdit.class);
//        startActivity(intent);
    }

    private void getLessonsPorcess() {
        try {
            loadLesson();
        } catch (Exception e) {
            POIWriteNewEXCELFile writeEXCELFile = new POIWriteNewEXCELFile(new File(destinationDirectory, "test" + fileExtension), sheetIndex, columnIndex, cellIndex, this);
            writeEXCELFile.copyAssets();
            getAllLessonsInCustomFolder(this);
            try {
                lessonIndex--;
                if (lessonIndex < 0) {
                    lessonIndex = 0;
                }
                loadLesson();
            } catch (Exception i) {
                lessonIndex = 0;
                loadLesson();
                i.printStackTrace();
            }
            e.printStackTrace();
        }
    }


    private static void setFadeInAnimation(Context c, ConstraintLayout layout, View v, int fade_in, int duration, int offset) {
        Animation mLoadAnimation = AnimationUtils.loadAnimation(c.getApplicationContext(), fade_in);
        mLoadAnimation.setDuration(duration);
        mLoadAnimation.setStartOffset(offset);
        mLoadAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });
        layout.startAnimation(mLoadAnimation);
    }

    private void createMelody() {
        int notes = 10;
        double[] pitches = new double[notes];
        double[] detunes = new double[notes];
        double[] durations = new double[notes];
        int rnd = new Random().nextInt();

        Random random = new Random();
        // Define the C minor scale as an array of integers representing the MIDI pitches of the notes
        int[] cMinorScale = {60, 62, 63, 65, 67, 68, 70, 72};
        // Generate at least 50 notes

        for (int i = 0; i < notes; i++) {
            // Randomly select a note from the scale
            int pitch = cMinorScale[random.nextInt(cMinorScale.length)];
            // Randomly set the duration of the note between 2 and 5 seconds
            int duration = random.nextInt(3000) + 2000;
            // Randomly set the detune of the note between -50 and 50 cents
            int detune = random.nextInt(100) - 50;
            // Play the note with the randomly selected pitch, duration, and detune
            pitches[i] = pitch;
            durations[i] = duration;
            detunes[i] = detune;


        }
        if (noteCounter < notes) {


        }
        noteCounter++;
    }

    @Override
    protected void onStop() {
        super.onStop();
//        toneGenerator.release();
    }

    public void loadLesson() {
        File file = new File(getApplicationContext().getFilesDir(), lessonNames[lessonIndex]);
        if (file.exists()) {
            FilesReadLesson.readFromExistingWordFileInInternalStorage(this);
        } else {
            POIReadEXCELFile poi = new POIReadEXCELFile(new File(destinationDirectory, lessonNames[lessonIndex] + fileExtension), sheetIndex, columnIndex, cellIndex);
            loadedCardsAsList = poi.scanExcelByWhileLoopForWordListView();
            loadedCardsAsArray = poi.convertCardsArrayListToArray(loadedCardsAsList);
            cardlistLenght = loadedCardsAsList.size();
        }
    }


    private void switchAdvancedRepeatLogic() {
        if (Parameter.advancedRepeatLogicIndex == 4) {
            answerLogic.advancedRepeatLogic = AdvancedRepeatLogic.NONE;
        } else if (Parameter.advancedRepeatLogicIndex == 2) {
            answerLogic.advancedRepeatLogic = AdvancedRepeatLogic.RANDOM;
        } else if (Parameter.advancedRepeatLogicIndex == 1) {
            answerLogic.advancedRepeatLogic = AdvancedRepeatLogic.END;
        } else if (Parameter.advancedRepeatLogicIndex == 3) {
            answerLogic.advancedRepeatLogic = AdvancedRepeatLogic.COUNT;
        }
    }

    private void setMediaPlayer(Context context) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                new PlaySound(questionTxtVw, context).playSoundOnShow();
            }
        });
    }


    private void placeButtons() {
        float height = (float) (displayHeight * 0.69 - displayHeight);
        this.findViewById(R.id.btnContainer).setY(height);
    }


    @Override
    public void onBackPressed() {

    }

    private void adaptDisplayToModesOnAppAlreadyRunning() {
        if (orientationI == 0 && orientationTemporaryII == 0) {
            if (settingsWereUsed) {
                switchDayAndNight();
            }
        } else {
            switchDayAndNight();
        }
    }

    private void switchDayAndNight() {
        if (!nightMode && !saveBatteryMode) {
            setDayMode();
        } else {
            if (nightMode) {
                setNightMode();
            } else {
                setBatteryMode();
            }
        }
    }


    private void setBigLetters() {
        if (bigLetters) {
            questionTextSize = questionTextSize * 1.5;
            questionTextSizeII = questionTextSizeII * 1.5;
            answerTextSize = answerTextSize * 1.5;
            questionTxtVw.setTextSize((float) questionTextSize);
            questionTxtVwII.setTextSize((float) questionTextSizeII);
            answerTxtVw.setTextSize((float) answerTextSize);
        }
    }

    private void setOrientationListenerII() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            orientationTemporaryII = 0;
            placeButtons();
        } else {
            orientationTemporaryII = 1;
        }
    }

    private void setOrientationListenerI() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            orientationI = 0;
        } else {
            orientationI = 1;
        }
    }

    private void setUiElementsVisibility(int i) {
        btnLeft.setVisibility(i);
        btnSetToEnd.setVisibility(i);
        btnRight.setVisibility(i);
        questionTxtVw.setVisibility(i);
        menuIcon.setVisibility(i);
        menuIconEditCard.setVisibility(i);
        menuIconAdNewCard.setVisibility(i);
        hint.setVisibility(i);
        hintDirection.setVisibility(i);
        hintMode.setVisibility(i);
    }

    private void getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        displayHeight = displaymetrics.heightPixels;
        displayWidth = displaymetrics.widthPixels;
        Parameter.toastOffsetY = (int) displayHeight / 2;
        Parameter.toastOffsetX = (int) displayWidth / 2;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        answerLogic.answerIsVisible = false;
//        answerLogic.answerWasShownWhenDestroy = false;
        FilesWriteSettings.writeLessonSettings(this);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
        }
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        startApp();
    }


    private void getTextViewsTextSize() {
        TextView view = findViewById(R.id.questionTxtView);
        questionTextSize = view.getTextSize();
        view = findViewById(R.id.questionTxtVwII);
        questionTextSize = view.getTextSize();
        view = findViewById(R.id.answerTxtVw);
        answerTextSize = view.getTextSize();
//        menuIcon.setBackgroundColor(Color.WHITE);
    }


    private void setTextToUiElements() {
//        firstStart = false;
//        if (answerLogic.questionIsReversed) {
//            question = loadedCardsAsArray[0][questionCardIndex].replace("_", " ");
//            answer = loadedCardsAsArray[0][answerCardIndex].replace("_", " ");
//            questionHint = loadedCardsAsArray[0][questionCardHintIndexMarker].replace("_", " ");
////             showHint = Boolean.parseBoolean(loadedCardsAsArray[0][showHintIndexMarker]);
//            hintDirection.setText(StringsUI.direction[1]);
//        } else {
//            question = loadedCardsAsArray[0][answerCardIndex].replace("_", " ");
//            answer = loadedCardsAsArray[0][questionCardIndex].replace("_", " ");
//            questionHint = loadedCardsAsArray[0][questionCardHintIndexMarker].replace("_", " ");
////            showHint = Boolean.parseBoolean(loadedCardsAsArray[0][showHintIndexMarker]);
//            hintDirection.setText(StringsUI.direction[0]);
//        }
//        showHint = Boolean.parseBoolean(loadedCardsAsArray[0][showHintIndexMarker]);
//
//        question = question.replace("_", " ");
//        answer = answer.replace("_", " ");
//        questionHint = questionHint.replace("_", " ");
//        if (!questionHint.equals("#") && showHint && !questionHint.equals(StringsEXCEL.lessonTemplateEmpty[questionCardHintIndexMarker])) {
//            questionTxtVwII.setVisibility(View.VISIBLE);
//        } else {
//            questionTxtVwII.setVisibility(View.INVISIBLE);
//        }

        boolean showHint = Boolean.parseBoolean(loadedCardsAsArray[0][showHintIndexMarker]);
        if (!answerLogic.questionIsReversed) {
            question = loadedCardsAsArray[0][questionCardIndex].replace("_", " ");
            answer = loadedCardsAsArray[0][answerCardIndex].replace("_", " ");
            questionHint = loadedCardsAsArray[0][questionCardHintIndexMarker].replace("_", " ");
            hintDirection.setText(StringsUI.direction[1]);
        } else {
            question = loadedCardsAsArray[0][answerCardIndex].replace("_", " ");
            answer = loadedCardsAsArray[0][questionCardIndex].replace("_", " ");
            questionHint = loadedCardsAsArray[0][questionCardHintIndexMarker].replace("_", " ");
            hintDirection.setText(StringsUI.direction[0]);
        }

        question = question.replace("_", " ");
        answer = answer.replace("_", " ");
        questionHint = questionHint.replace("_", " ");
        if (!questionHint.equals("#") && showHint && !questionHint.equals(StringsEXCEL.lessonTemplateEmpty[questionCardHintIndexMarker])) {
            questionTxtVwII.setVisibility(View.VISIBLE);
        } else {
            questionTxtVwII.setVisibility(View.INVISIBLE);
        }

        btnLeft.setText(StringsUI.btnLeftText[0]);
        btnRight.setText(StringsUI.btnRightText[0]);
        hint.setText(StringsUI.hintText[0]);
        questionTxtVw.setText(question);
        questionTxtVwII.setText(questionHint);
        answerTxtVw.setText(answer);
        menuIcon.setText(StringsUI.menuIconSymbol);
//        menuIcon.setTextSize(60);

        btnSetToEnd.setText(StringsUI.btnSetToEnd[0]);
        runOnUiThread(() -> {
            questionTxtVw = adaptTextViewTextSize(questionTxtVw, Parameter.questionTextSizePercentage);
            questionTxtVwII = adaptTextViewTextSize(questionTxtVwII, Parameter.questionHintTextSizePercentage);
            answerTxtVw = adaptTextViewTextSize(answerTxtVw, Parameter.answerTextSizePercentage);
        });


        Arrays.progressCounter(loadedCardsAsArray, questioningLayout);
    }


    public void getViews() {
        questionTxtVw = findViewById(R.id.questionTxtView);
        questionTxtVwII = findViewById(R.id.questionTxtVwII);
        answerTxtVw = findViewById(R.id.answerTxtVw);
        hint = findViewById(R.id.hint);
        hintDirection = findViewById(R.id.hintDirection);
        hintMode = findViewById(R.id.hintMode);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        btnSetToEnd = findViewById(R.id.btnSetToEnd);
        btnContainer = (LinearLayout) findViewById(R.id.btnContainer);
        menuIconEditCard = findViewById(R.id.menuIconEditCard);
        menuIconAdNewCard = findViewById(R.id.menuIconAdNewCard);
        menuIcon = findViewById(R.id.menuIcon);
    }


    private void setTextViewOnCLick() {
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        hint.setOnClickListener(this);
        questionTxtVw.setOnClickListener(this);
        btnSetToEnd.setOnClickListener(this);
        answerTxtVw.setOnClickListener(this);
        menuIcon.setOnClickListener(this);
        menuIconEditCard.setOnClickListener(this);
        menuIconAdNewCard.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLeft) {
            if (!moveButtons) {
                answerLogic.inputLeft();
                adaptTextViewTextSize(findViewById(R.id.questionTxtView), Parameter.questionTextSizePercentage);
                adaptTextViewTextSize(findViewById(R.id.questionTxtVwII), Parameter.questionHintTextSizePercentage);
                adaptTextViewTextSize(findViewById(R.id.answerTxtVw), Parameter.answerTextSizePercentage);
            } else {
                if (!buttonLayoutWasMovedUpwards) {
                    buttonLayoutWasMovedUpwards = true;
                    tempButtonLayoutY = 0;
                }
                buttonLayoutYCounter--;
                buttonLayoutY = buttonLayoutY - 10;
                tempButtonLayoutY = tempButtonLayoutY - 10;
//                Toast.makeText(this, "" + buttonLayoutY, Toast.LENGTH_SHORT);
//                btnContainer.setY(btnContainer.getY() + dpFromPx(this, tempButtonLayoutY));
            }

            fadeAction.run();

        }
        if (view.getId() == R.id.btnRight) {
            if (!moveButtons) {
                answerLogic.inputRight();
                adaptTextViewTextSize(findViewById(R.id.questionTxtView), Parameter.questionTextSizePercentage);
                adaptTextViewTextSize(findViewById(R.id.questionTxtVwII), Parameter.questionHintTextSizePercentage);
                adaptTextViewTextSize(findViewById(R.id.answerTxtVw), Parameter.answerTextSizePercentage);
            } else {
                if (buttonLayoutWasMovedUpwards) {
                    buttonLayoutWasMovedUpwards = false;
                    tempButtonLayoutY = 0;
                }
                buttonLayoutYCounter++;

                buttonLayoutY = buttonLayoutY + 10;
                tempButtonLayoutY = tempButtonLayoutY + 10;
            }
            fadeAction.run();
        }
        if (view.getId() == R.id.btnSetToEnd) {
            answerLogic.setCardToTheEnd = true;
            answerLogic.answerIsVisible = true;
            questionTxtVwII.setVisibility(View.INVISIBLE);
            answerLogic.inputRight();
            adaptTextViewTextSize(findViewById(R.id.questionTxtView), Parameter.questionTextSizePercentage);
            adaptTextViewTextSize(findViewById(R.id.questionTxtVwII), Parameter.questionHintTextSizePercentage);
            adaptTextViewTextSize(findViewById(R.id.answerTxtVw), Parameter.answerTextSizePercentage);
//            Arrays.hideHintLogic(questioningLayout, false);
            fadeAction.run();


        }
        if (view.getId() == R.id.answerTxtVw) {
            answerHint = loadedCardsAsArray[0][answerCardHintIndexMarker];
            PlaySound playSound = new PlaySound(questionTxtVw, this);
            playSound.playSoundOnShow();
        }

        if (view.getId() == R.id.hint) {
            answerLogic.switchAnswerToQuestion();
            if (!answerLogic.questionIsReversed) {
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, Parameter.toastOffsetX, Parameter.toastOffsetY);
                Toast.makeText(this, StringsUI.hintClickText[1], Toast.LENGTH_SHORT).show();
            } else {
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, Parameter.toastOffsetX, Parameter.toastOffsetY);
                Toast.makeText(this, StringsUI.hintClickText[0], Toast.LENGTH_SHORT).show();
            }
            FilesWriteSettings.writeLessonSettings(this);
        }

        if (view.getId() == R.id.questionTxtView) {
            if (!moveButtons) {
                try {
                    PlaySound playSound = new PlaySound(questionTxtVw, this);
                    playSound.playSoundOnShow();
                } catch (Exception e) {

                }
            } else {
                goBackFromAdjustButtonsMode();
            }
//            NewMenu newMenu = new NewMenu(this);
        }

        if (view.getId() == R.id.questionTxtVwII) {
//            NewMenu newMenu = new NewMenu(this);
            hideHint = !hideHint;
            String text = StringsUI.showHintText;
            if (hideHint) {
                text = StringsUI.hideHintText;
            }
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

        }


        if (view.getId() == R.id.menuIcon) {
            ViewMenu menu = new ViewMenu(this, this, mode, questioningLayout);
            menu.getWindow().setBackgroundDrawable(new ColorDrawable(R.drawable.curved_window));
            menu.show();
            if (moveButtons) {
                goBackFromAdjustButtonsMode();
            }
        }
        if (view.getId() == R.id.menuIconEditCard) {

            ViewMenuAd menuInput = new ViewMenuAd(this, this, mode, questioningLayout, loadedCardsAsArray, false, false);
            menuInput.getWindow().setBackgroundDrawable(new ColorDrawable(R.drawable.curved_window));
            menuInput.show();
            menuInput.setCancelable(false);
            menuInput.setCanceledOnTouchOutside(false);
//            questioningLayout.setAlpha(0);
//            setFadeInAnimation(this,questioningLayout,null,android.R.anim.fade_out , 200, 300);

        }
        if (view.getId() == R.id.menuIconAdNewCard) {

            ViewMenuAd menuInput = new ViewMenuAd(this, this, mode, questioningLayout, loadedCardsAsArray, true, false);
            menuInput.getWindow().setBackgroundDrawable(new ColorDrawable(R.drawable.curved_window));
            menuInput.show();
            menuInput.setCancelable(false);
            menuInput.setCanceledOnTouchOutside(false);
        }
    }

    private void goBackFromAdjustButtonsMode() {
        if (!answerLogic.answerIsVisible) {
            btnLeft.setText(StringsUI.btnLeftText[0]);
            btnRight.setText(StringsUI.btnRightText[0]);
        } else {
            btnLeft.setText(StringsUI.btnLeftText[1]);
            btnRight.setText(StringsUI.btnRightText[1]);
        }
        questionTxtVw.setText(StringsUI.tempQuestionText);
        fadeAction.run();
        adaptTextViewTextSize(findViewById(R.id.questionTxtView), Parameter.questionTextSizePercentage);
        adaptTextViewTextSize(findViewById(R.id.questionTxtVwII), Parameter.questionHintTextSizePercentage);
        adaptTextViewTextSize(findViewById(R.id.answerTxtVw), Parameter.answerTextSizePercentage);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        FilesWriteSettings.writeLessonSettings(this);
        FilesWriteSettings.writeStartupSettings(this);
        moveButtons = false;
    }


    private void setColors() {
        questionTxtVw.setTextColor(Colors.btn_text_color);
        questionTxtVwII.setTextColor(Colors.btn_text_color);
        answerTxtVw.setTextColor(Colors.btn_text_color);

        if (mode != 0) {
            hint.setTextColor(Color.WHITE);
            hintDirection.setTextColor(Color.WHITE);
            hintMode.setTextColor(Color.WHITE);
            menuIcon.setTextColor(Color.WHITE);
        } else {
            hint.setTextColor(Colors.btn_text_color);
            hintDirection.setTextColor(Colors.btn_text_color);
            hintMode.setTextColor(Colors.btn_text_color);
            menuIcon.setTextColor(Colors.btn_text_color);
        }


        questioningLayout.setBackgroundColor(Colors.background);

        btnLeft.setTextColor(Colors.btn_text_color);
        btnRight.setTextColor(Colors.btn_text_color);
        btnSetToEnd.setTextColor(Colors.btn_text_color);

        btnLeft.setBackground(Colors.btnBackground);
        btnRight.setBackground(Colors.btnBackground);
        btnSetToEnd.setBackground(Colors.btnBackground);


        menuIconEditCard.setBackground(Colors.btn_edit_card_background);
        menuIconAdNewCard.setBackground(Colors.btn_add_card_background);

        menuIconEditCard.setAlpha(0.3f);
        menuIconAdNewCard.setAlpha(0.3f);
        hint.setAlpha(0.3f);
        hintDirection.setAlpha(0.3f);
        hintMode.setAlpha(0.3f);
        menuIcon.setAlpha(0.3f);

    }

    public void setDayMode() {
        mode = 0;
        questioningLayout.setBackgroundColor(Color.WHITE);

        Colors.setColorMode(this, mode);
        setColors();
    }

    public void setBatteryMode() {
        mode = 1;
        questioningLayout.setBackgroundColor(Color.BLACK);
        Colors.setColorMode(this, mode);
        setColors();
    }

    public void setNightMode() {
        mode = 2;
        questioningLayout.setBackgroundColor(Color.BLACK);
        Colors.setColorMode(this, mode);
        setColors();
    }

//    public static void playSoundOnShow(TextView view) {
//        String soundFileDirectory = Arrays.questioning[1][Arrays.fileInfoI];
//        String fileName = view.getText().toString().replaceAll(" ", "_").replace("/", "").replace("(", "").replace(")", "").replace("|", "");
//        if (!new File(soundFileDirectory).exists()) {
//            new File(soundFileDirectory).mkdir();
//        }
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    mediaPlayer = new MediaPlayer();
//
//                    AssetFileDescriptor afd = c.getAssets().openFd(soundFileDirectory + File.separator + fileName + UIStrings.wav);
//                    mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
//                    mediaPlayer.setOnCompletionListener(MediaPlayer::release);
//                    mediaPlayer.prepare();
//                    mediaPlayer.start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread.setDaemon(true);
//        thread.start();
//    }


    public void setFadeAnimation() {
        View viewI = findViewById(R.id.questionTxtView);
        View answer = findViewById(R.id.answerTxtVw);

        fadeAction = new Runnable() {
            @Override
            public void run() {
                mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                mLoadAnimation.setDuration(newCardTransitionDuration);
                viewI.startAnimation(mLoadAnimation);

                if (answerLogic.answerIsVisible && answerLogic.alwaysShowQuestionWithAnswer) {
                    mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                    mLoadAnimation.setDuration(newCardTransitionDuration);
                } else {
                    mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
                    mLoadAnimation.setDuration(0);
                }
                answer.startAnimation(mLoadAnimation);
            }


        };
    }


    public void setFadeAnimationHint() {
        View viewII = findViewById(R.id.questionTxtVwII);
        fadeActionHint = new Runnable() {
            public void run() {
                mLoadAnimationHint = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
                mLoadAnimationHint.setDuration(newCardTransitionDurationHint);
                mLoadAnimationHint.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        viewII.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                viewII.startAnimation(mLoadAnimationHint);
            }
        };


    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}