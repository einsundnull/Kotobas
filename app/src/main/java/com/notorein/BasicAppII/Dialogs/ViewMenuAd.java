package com.notorein.BasicAppII.Dialogs;

import static com.notorein.BasicAppII.AnswerLogic.showHint;
import static com.notorein.BasicAppII.Arrays.answerCardIndex;
import static com.notorein.BasicAppII.Arrays.answerCardRepeatIndexMarker;
import static com.notorein.BasicAppII.Arrays.answerCountFalseIndexMarker;
import static com.notorein.BasicAppII.Arrays.answerCountRightIndexMarker;
import static com.notorein.BasicAppII.Arrays.answerRepeatTimeIndexMarker;
import static com.notorein.BasicAppII.Arrays.answerTimeStepIndexMarker;
import static com.notorein.BasicAppII.Arrays.editedCardIndex;
import static com.notorein.BasicAppII.Arrays.fileInfoII;
import static com.notorein.BasicAppII.Arrays.firstCardInRowTempEdit;
import static com.notorein.BasicAppII.Arrays.lessonIndex;
import static com.notorein.BasicAppII.Arrays.lessonNames;
import static com.notorein.BasicAppII.Arrays.lessonsTemplateCardMetaInfoArrayMinLength;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsArray;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsList;
import static com.notorein.BasicAppII.Arrays.questionCardHintIndexMarker;
import static com.notorein.BasicAppII.Arrays.questionCardIndex;
import static com.notorein.BasicAppII.Arrays.questionCardRepeatIndexMarker;
import static com.notorein.BasicAppII.Arrays.questionCountFalseIndexMarker;
import static com.notorein.BasicAppII.Arrays.questionCountRightIndexMarker;
import static com.notorein.BasicAppII.Arrays.questionRepeatTimeIndexMarker;
import static com.notorein.BasicAppII.Arrays.questionTimeStepIndexMarker;
import static com.notorein.BasicAppII.Arrays.sortIndexCard;
import static com.notorein.BasicAppII.Arrays.sortIndexCardProgress;
import static com.notorein.BasicAppII.Arrays.sortLesson;
import static com.notorein.BasicAppII.Parameter.Parameter.allowToAddNewCard;
import static com.notorein.BasicAppII.Parameter.Parameter.allowToReverseCard;
import static com.notorein.BasicAppII.Parameter.Parameter.allowToStoreCard;
import static com.notorein.BasicAppII.Parameter.Parameter.displayHeight;
import static com.notorein.BasicAppII.Parameter.Parameter.displayWidth;
import static com.notorein.BasicAppII.Parameter.Parameter.editTextIndex;
import static com.notorein.BasicAppII.Parameter.Parameter.isRecording;
import static com.notorein.BasicAppII.Parameter.Parameter.makeNewCard;
import static com.notorein.BasicAppII.Parameter.Parameter.sortArrayBeforeEditingInAddMenu;
import static com.notorein.BasicAppII.Parameter.Parameter.text;
import static com.notorein.BasicAppII.Strings.StringsEXCEL.lessonTemplateEmpty;
import static com.notorein.BasicAppII.Strings.StringsEXCEL.removeRegex;
import static com.notorein.BasicAppII.UISettingsItems.createDrawable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Arrays;
import com.notorein.BasicAppII.Colors;
import com.notorein.BasicAppII.Files.FilesWriteLessons;
import com.notorein.BasicAppII.POI.POIStoreExcelFile;
import com.notorein.BasicAppII.Parameter.Parameter;
import com.notorein.BasicAppII.Strings.StringsEXCEL;
import com.notorein.BasicAppII.Strings.StringsUI;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ViewMenuAd extends Dialog implements View.OnClickListener {


    //    private String[][] firstCardInRowInAddMenu;
//    private boolean sortArrayBeforeEditingInAddMenu;
    //    EditText inputField;
    //    private MediaRecorder recorder;
    //    private View divider;
    Activity activity;
    Context context;
    private static final String TAG = "FileUtils";
    private static final String AUDIO_FILE_NAME = "audio.3gp";

    private MediaRecorder mRecorder = null;
    private MediaRecorder recorder;
    EditText fileNameField;
    public ConstraintLayout layout;
    private CheckBox checkBoxReverse;
    private CheckBox checkBoxShowHint;

    private ImageView btn_close;
    private ImageView btn_store;
    private ImageView btn_copy;
    private ImageView btn_delete;
    private ImageView imageViewSwapCardQuestion;
    private ImageView btn_swap;
    //    public TextView answerHintTextField;
    public TextView answerTextField;
    public TextView questionHintTextField;
    public TextView questionTextField;
    public TextView title;

    //    public static int editTextIndex = 0;
    private int layoutHeight;
    private int layoutWidth;
    private int mode;

//    public static String text;
//    private String[] firstCardInRowTemp;

    String answer;
    String answerHint;
    String question;
    String questionHint;
    TextView recordButton;
    //    public TextView about;
//    private boolean allowToAddNewCard;
//    private boolean allowToReverseCard;
//    private boolean allowToStoreCard = true;
//    private boolean isRecording;
//    private boolean makeNewCard;
//    private boolean showHint;
    private TextView btn_right;
    private TextView btn_left;
    private TextView txtViewInfo;


    public ViewMenuAd(Context context, Activity activity, int mode, ConstraintLayout layout, String[][] firstCardInRow, boolean makeNewCard, boolean sortArrayBeforeEditing) {
        super(activity);
        this.context = context;
        this.mode = mode;
        this.activity = activity;
        this.layout = layout;
        Parameter.makeNewCard = makeNewCard;
        Arrays.firstCardInRowInAddMenu = firstCardInRow;
        sortArrayBeforeEditingInAddMenu = sortArrayBeforeEditing;
//        if (sortArrayBeforeEditing) {
//            this.firstCardInRow = sortLesson(firstCardInRow)[editedCardIndex];
//        } else {
//            this.firstCardInRow = firstCardInRow[editedCardIndex];
//        }

//        if (sortArrayBeforeEditing) {
//            this.firstCardInRowTemp = sortLesson(firstCardInRow)[editedCardIndex];
//        } else {
//            this.firstCardInRowTemp = firstCardInRow[editedCardIndex];
//        }

        layoutWidth = (int) (displayWidth);
        layoutHeight = (int) (displayHeight);
    }

    public void setBasicInputValuesToEditText(String[] firstCardInRow) {
        this.question = firstCardInRow[Arrays.questionCardIndex];
        this.questionHint = firstCardInRow[Arrays.questionCardHintIndexMarker];
        this.answer = firstCardInRow[Arrays.answerCardIndex];
        this.answerHint = firstCardInRow[Arrays.answerCardHintIndexMarker];
        allowToReverseCard = Boolean.parseBoolean(firstCardInRow[Arrays.allowReverseCardIndexQuestionMarker]);
        showHint = Boolean.parseBoolean(firstCardInRow[Arrays.showHintIndexMarker]);
    }

    public Activity getActivity() {
        return activity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.view_menu_ad);
        title = findViewById(R.id.menuTitle);
        layout = findViewById(R.id.menuLayoutAd);
        questionTextField = findViewById(R.id.questionTextField);
        questionHintTextField = findViewById(R.id.answerTextField);
        answerTextField = findViewById(R.id.questionHintTextField);
//        answerHintTextField = findViewById(R.id.answerHintTextField);
        checkBoxReverse = findViewById(R.id.checkBoxReverse);
        checkBoxShowHint = findViewById(R.id.checkBoxShowHint);
        btn_close = layout.findViewById(R.id.btn_close);
        btn_store = layout.findViewById(R.id.btn_store_card);
        btn_delete = layout.findViewById(R.id.btn_delete);
        btn_copy = layout.findViewById(R.id.btn_copy);
        btn_left = layout.findViewById(R.id.btn_left);
        btn_right = layout.findViewById(R.id.btn_right);
        txtViewInfo = layout.findViewById(R.id.txtViewInfo);

        btn_swap = layout.findViewById(R.id.btn_swap);
        imageViewSwapCardQuestion = layout.findViewById(R.id.imageViewSwapCardQuestion);
        recordButton = findViewById(R.id.record_button);
        layout.setMinWidth((int) (layoutWidth));
        layout.setMinHeight((int) layoutHeight);
        title.setMinWidth((int) (layoutWidth * 1));
        questionTextField.setMinWidth((int) (layoutWidth * 1));
        questionHintTextField.setMinWidth((int) (layoutWidth * 1));
        answerTextField.setMinWidth((int) (layoutWidth * 1));
//        answerHintTextField.setMinWidth((int) (layoutWidth * 1));
        if (sortArrayBeforeEditingInAddMenu) {
            firstCardInRowTempEdit = sortLesson(loadedCardsAsArray)[editedCardIndex];
        } else {
            firstCardInRowTempEdit = loadedCardsAsArray[editedCardIndex];
        }

        // Here I check wether the card list contains empty cards. If so no new card will be added until
        // all empty cars are filled.
        setTextToElements(true);

        checkBoxReverse.setOnClickListener(this);
        checkBoxShowHint.setOnClickListener(this);
        btn_close.setOnClickListener(this);
        btn_store.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_copy.setOnClickListener(this);
        imageViewSwapCardQuestion.setOnClickListener(this);
        btn_swap.setOnClickListener(this);
        recordButton.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);

        btn_copy.setVisibility(View.INVISIBLE);
//        btn_delete.setVisibility(View.INVISIBLE);

        setColor();
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            questionHintTextField.setEnabled(true);
        } else {
            questionHintTextField.setEnabled(false);
        }

//        answerHintTextField.setVisibility(View.INVISIBLE);
        btn_swap.setVisibility(View.INVISIBLE);

        recordButton.setBackground(createDrawable(Color.GRAY, Color.RED, 100, 10));
        setEditTextOnCLick();
        setRecordButtonOnClick();
        Log.i(TAG, "onClick:editedCardIndex " + editedCardIndex);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        activity.getActionBar().hide();
        return true;
    }

    private void setTextToElements(boolean checkForEmptyCards) {
//        if (sortArrayBeforeEditing) {
//            this.firstCardInRowTemp = sortLesson(firstCardInRow)[editedCardIndex];
//        } else {
//            this.firstCardInRowTemp = firstCardInRow[editedCardIndex];
//        }
        editTextIndex = 0;
        if (checkForEmptyCards) {
            allowToAddNewCard = Arrays.checkForEmptyCardsInEdit(loadedCardsAsArray, lessonTemplateEmpty);
        }

        if (!makeNewCard) {
            title.setText(StringsUI.menuDialogEdit);
            loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
            setBasicInputValuesToEditText(firstCardInRowTempEdit);
        } else {
            title.setText(StringsUI.menuDialogAd);
            setBasicInputValuesToEditText(lessonTemplateEmpty);
        }
        if (question.equals(lessonTemplateEmpty[questionCardIndex])) {
            questionTextField.setHint(question);
        } else {
            questionTextField.setText(question);
        }
        if (questionHint.equals(lessonTemplateEmpty[questionCardHintIndexMarker])) {
            questionHintTextField.setHint(questionHint);
        } else {
            questionHintTextField.setText(questionHint);
        }
        if (answer.equals(lessonTemplateEmpty[answerCardIndex])) {
            answerTextField.setHint(answer);
        } else {
            answerTextField.setText(answer);
        }
//        if (answerHint.equals(lessonTemplateEmpty[answerCardIndex])) {
//            answerHintTextField.setHint(answerHint);
//        } else {
//            answerHintTextField.setText(answerHint);
//        }

        checkBoxReverse.setText(StringsEXCEL.ckBxAllowReverseText);
        checkBoxShowHint.setText(StringsEXCEL.ckBxShowQuestionHintText);
        btn_left.setText(StringsUI.btn_left_text);
        btn_right.setText(StringsUI.btn_right_text);
        checkBoxReverse.setChecked(allowToReverseCard);
        checkBoxShowHint.setChecked(showHint);
        txtViewInfo.setText((editedCardIndex + 1) + "/" + loadedCardsAsArray.length);
    }

    private EditText setOnTypedChanged(EditText inputField) {
        inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text is changed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = questionHintTextField.getText().toString();
                questionHintTextField.setHint(input);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text is changed
            }
        });
        return inputField;

    }

    private static void setFadeInAnimation(Context c, ConstraintLayout layout, View v) {
        Animation mLoadAnimation = AnimationUtils.loadAnimation(c.getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(3500);
        mLoadAnimation.setStartOffset(0);
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

    private void setRecordButtonOnClick() {
//        recordButton.setOnClickListener(c -> {
//            File file = null;
//            if (!isRecording) {
//                file = new File(Arrays.lessonFilesInCustomFolder[lessonIndex].getPath().replace(ParameterEXCEL.fileExtension, ""));
//                if (!file.exists()) {
//                    file.mkdir();
//                }
//                file = new File(file, loadedCardsAsArray[editedCardIndex][editTextIndex] + ".mp3");
//                isRecording = true;
//                Log.i(TAG, "setRecordButtonOnClick: " + activity.getFilesDir());
//                Log.i(TAG, "onTouch: " + file);
////                prepareForRecording(file);
//                startRecording(file);
//                recordButton.setBackground(createDrawable(Color.GRAY, Color.GREEN, 100, 10));
//            } else {
//                isRecording = false;
//                stopRecording(file);
//                recordButton.setBackground(createDrawable(Color.GRAY, Color.RED, 100, 10));
//            }
//        });

//        recordButton.setOnTouchListener(new View.OnTouchListener() {
//
//
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                File file = null;
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        file = new File(Arrays.lessonFilesInCustomFolder[lessonIndex].getPath().replace(ParameterEXCEL.fileExtension,""));
//                        file.mkdir();
//                        file = new File(file, loadedCardsAsArray[editedCardIndex][editTextIndex]+".mp3");
//                        Log.i(TAG, "onTouch: " + file+".mp3");
//                        prepareForRecording(file);
////                        startRecording();
//                        break;
//                    case MotionEvent.ACTION_UP:
////                         Stoprecording
////                        stopRecording(file);
//                        break;
//                }
//                return true;
//            }
//        });


    }

    public void startRecording(File file) {
        try {
            recorder = new MediaRecorder();
            recorder.setOutputFile(file);
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording(File file) {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
//            prepareForRecording(file);
        }
    }


//    public void prepareForRecording(File file) {
//
////        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
////        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
////        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//    }

    private void setEditTextOnCLick() {
        questionTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionTextField.requestFocus();
                editTextIndex = 0;
                text = questionTextField.getText().toString();
//                questionTextField.setBackground(createDrawable(Color.YELLOW, Colors.background, 5, 3));
//                questionHintTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                answerTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                answerHintTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
            }
        });

        questionHintTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionHintTextField.requestFocus();
                editTextIndex = 1;
                text = questionHintTextField.getText().toString();
//                questionTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                questionHintTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                answerTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                answerHintTextField.setBackground(createDrawable(Color.YELLOW, Colors.background, 5, 3));
            }
        });

        answerTextField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerTextField.requestFocus();
                editTextIndex = 2;
                text = answerTextField.getText().toString();

//                questionTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                questionHintTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                answerTextField.setBackground(createDrawable(Color.YELLOW, Colors.background, 5, 3));
//                answerHintTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
            }
        });

    }

    @Override
    public void onClick(View v) {
        boolean showAbout = true;
        boolean[] allowToStore = {true, true, true};
        Intent intent;
        String temp = "";
//        if (v.getId() == R.id.answerHintTextField) {
//            editTextIndex = 3;
//            text = answerHintTextField.getText().toString();
//
////            questionTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
////            questionHintTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
////            answerTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
////            answerHintTextField.setBackground(createDrawable(Color.YELLOW, Colors.background, 5, 3));
//
//        }
        if (v.getId() == R.id.record_button) {
            isRecording = !isRecording;
            int color;
            if (isRecording) {
                color = Color.GREEN;
            } else {
                color = Color.RED;
            }

            recordButton.setBackground(createDrawable(Color.GRAY, color, 100, 10));
        }
        if (v.getId() == R.id.btn_delete) {
            if (loadedCardsAsArray.length > 1) {
                if (!makeNewCard) {
                    ViewOptions options = new ViewOptions(activity, mode, StringsUI.dialogDeleteCard, StringsUI.toastTextDeleteCard, () -> {
                        LinkedList<LinkedList<String>> loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
                        loadedCardsAsList.remove(editedCardIndex);
                        loadedCardsAsArray = Arrays.readListToArray(loadedCardsAsList);
                        String[][] tempArray = Arrays.sortLesson(loadedCardsAsArray);
                        POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArray, lessonNames[lessonIndex]);
                        FilesWriteLessons.writeFileWords(context, loadedCardsAsArray);
                    try {
                        poiStoreExcelFile.writeToExcel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        makeNewCard = false;
//                    sortArrayBeforeEditing = false;
//                    ActivityCards.removeCardLogic();
                        editedCardIndex--;
                        if (editedCardIndex < 0) {
                            editedCardIndex = 0;
                        }
                        txtViewInfo.setText((editedCardIndex + 1) + "/" + loadedCardsAsArray.length);
                        if (sortArrayBeforeEditingInAddMenu) {
                            firstCardInRowTempEdit = sortLesson(loadedCardsAsArray)[editedCardIndex];
                        } else {
                            firstCardInRowTempEdit = loadedCardsAsArray[editedCardIndex];
                        }
                        setTextToElements(false);
                    });
                    try {
                        options.show();
                    } catch (Exception e) {

                    }

                }
            } else {
                if (!makeNewCard) {
                    Toast.makeText(context, StringsUI.lastCard, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, StringsUI.emptyCard, Toast.LENGTH_LONG).show();
                }

            }
        }
        if (v.getId() == R.id.btn_copy) {
            loadedCardsAsArray = Arrays.copyCard(loadedCardsAsArray, editedCardIndex);
            String[][] tempII = loadedCardsAsArray;
            loadedCardsAsList = Arrays.readArrayToList(tempII);
            tempII = sortLesson(tempII);
            tempII = Arrays.sortLessonCopy(tempII, editTextIndex);
            POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempII, lessonNames[lessonIndex]);
            FilesWriteLessons.writeFileWords(context, loadedCardsAsArray);
        }
        if (v.getId() == R.id.btn_left) {
            makeNewCard = false;
            sortArrayBeforeEditingInAddMenu = false;
            editedCardIndex--;
            if (editedCardIndex < 0) {
                editedCardIndex = 0;
            }
            txtViewInfo.setText((editedCardIndex + 1) + "/" + loadedCardsAsArray.length);
            if (sortArrayBeforeEditingInAddMenu) {
                firstCardInRowTempEdit = sortLesson(loadedCardsAsArray)[editedCardIndex];
            } else {
                firstCardInRowTempEdit = loadedCardsAsArray[editedCardIndex];
            }
            setTextToElements(false);
            Log.i(TAG, "onClick:editedCardIndex " + editedCardIndex + "  " + loadedCardsAsArray.length);
        }
        if (v.getId() == R.id.btn_right) {
            makeNewCard = false;
            editedCardIndex++;

            if (editedCardIndex >= loadedCardsAsArray.length) {
                editedCardIndex = loadedCardsAsArray.length - 1;
            }
            txtViewInfo.setText((editedCardIndex + 1) + "/" + loadedCardsAsArray.length);

            if (sortArrayBeforeEditingInAddMenu) {
                firstCardInRowTempEdit = sortLesson(loadedCardsAsArray)[editedCardIndex];
            } else {
                firstCardInRowTempEdit = loadedCardsAsArray[editedCardIndex];
            }
            setTextToElements(false);
            Log.i(TAG, "onClick:editedCardIndex " + editedCardIndex);
        }
        if (v.getId() == R.id.checkBoxReverse) {
            allowToReverseCard = checkBoxReverse.isChecked();
        }
        if (v.getId() == R.id.checkBoxShowHint) {
            showHint = checkBoxShowHint.isChecked();
        }
        if (v.getId() == R.id.btn_close) {
            makeNewCard = false;
            Parameter.useFadeAnimationInQuestionLayout = false;
            activity.recreate();

            cancel();
        }
        if (v.getId() == R.id.imageViewSwapCardQuestion) {
            String tempQ = questionTextField.getText().toString();
            String tempA = answerTextField.getText().toString();
            questionTextField.setText(tempA);
            answerTextField.setText(tempQ);

        }
//        if (v.getId() == R.id.btn_swap) {
//            String tempQ = questionHintTextField.getText().toString();
//            String tempA = answerHintTextField.getText().toString();
//            questionHintTextField.setText(tempA);
//            answerHintTextField.setText(tempQ);
//        }
        if (v.getId() == R.id.btn_store_card) {
            storeLogic(questionTextField, questionHintTextField, answerTextField);
//            storeCard(questionTextField, questionHintTextField, answerTextField);
        }
    }


    public void setColor() {
        layout.setBackgroundColor(Colors.background);
        title.setBackgroundColor(Colors.background);
        if (mode == 0) {
            btn_right.setTextColor(Colors.btn_text_color);
            btn_left.setTextColor(Colors.btn_text_color);
            txtViewInfo.setTextColor(Colors.btn_text_color);
            questionTextField.setBackgroundColor(Colors.hint_text_color);
            questionHintTextField.setBackgroundColor(Colors.hint_text_color);
            answerTextField.setBackgroundColor(Colors.hint_text_color);
//            answerHintTextField.setBackgroundColor(Colors.hint_text_color);

            questionTextField.setHintTextColor(Colors.btn_text_color);
            questionHintTextField.setHintTextColor(Colors.btn_text_color);
            answerTextField.setHintTextColor(Colors.btn_text_color);
//            answerHintTextField.setHintTextColor(Colors.btn_text_color);
        } else {
            btn_right.setTextColor(Colors.btn_text_color);
            btn_left.setTextColor(Colors.btn_text_color);
            txtViewInfo.setTextColor(Colors.btn_text_color);
            questionTextField.setBackgroundColor(Colors.btn_text_color);
            questionHintTextField.setBackgroundColor(Colors.btn_text_color);
            answerTextField.setBackgroundColor(Colors.btn_text_color);
//            answerHintTextField.setBackgroundColor(Colors.btn_text_color);

            questionTextField.setHintTextColor(Colors.hint_text_color);
            questionHintTextField.setHintTextColor(Colors.hint_text_color);
            answerTextField.setHintTextColor(Colors.hint_text_color);
//            answerHintTextField.setHintTextColor(Colors.hint_text_color);
        }

        title.setTextColor(Colors.btn_text_color);
        if (mode == 0) {
            questionTextField.setTextColor(Colors.btn_text_color);
            questionHintTextField.setTextColor(Colors.btn_text_color);
            answerTextField.setTextColor(Colors.btn_text_color);
//            answerHintTextField.setTextColor(Colors.btn_text_color);
            checkBoxShowHint.setTextColor(Colors.btn_text_color);
            checkBoxReverse.setTextColor(Colors.btn_text_color);
        } else {
            questionTextField.setTextColor(Colors.background);
            questionHintTextField.setTextColor(Colors.background);
            answerTextField.setTextColor(Colors.background);
//            answerHintTextField.setTextColor(Colors.background);
            checkBoxShowHint.setTextColor(Colors.btn_text_color);
            checkBoxReverse.setTextColor(Colors.btn_text_color);
        }

        checkBoxShowHint.setButtonTintList(ColorStateList.valueOf(Colors.btn_text_color));
        checkBoxReverse.setButtonTintList(ColorStateList.valueOf(Colors.btn_text_color));
        btn_close.setColorFilter(Colors.btn_text_color);
        btn_store.setColorFilter(Colors.btn_text_color);
        btn_delete.setColorFilter(Colors.btn_text_color);
        btn_copy.setColorFilter(Colors.btn_text_color);
        imageViewSwapCardQuestion.setColorFilter(Colors.btn_text_color);
        imageViewSwapCardQuestion.setColorFilter(Colors.btn_text_color);
        imageViewSwapCardQuestion.setBackground(createDrawable(Colors.btn_text_color, Colors.background, 3, 3));
    }


    private String getOutputFile(String folderName, String fileName) {
        // Get the internal storage directory for the app
        File dir = activity.getFilesDir();
        // Create a new directory with the specified name
        File folder = new File(dir, folderName);
        folder.mkdir();
        // Create a new file with the specified name in the newly created directory
        File file = new File(folder, fileName);
        return file.getAbsolutePath();
    }

    public void storeLogic(TextView questionTextField, TextView questionHintTextField, TextView answerTextField) {
        allowToStoreCard = true;
        String questionText = questionTextField.getText().toString();
        String questionHintText = questionHintTextField.getText().toString();
        String answerText = answerTextField.getText().toString();
        String answerHintText = "#";
        boolean[] allowToStore = {true, true, true};
        if (questionText.isEmpty() || questionText.equals(lessonTemplateEmpty[questionCardIndex])) {
            questionTextField.setText(lessonTemplateEmpty[questionCardIndex]);
            questionText = lessonTemplateEmpty[questionCardIndex];
            allowToStore[0] = false;
        }
        if (questionHintText.isEmpty() || questionHintText.equals(lessonTemplateEmpty[questionCardHintIndexMarker])) {
            questionHintTextField.setText(lessonTemplateEmpty[questionCardHintIndexMarker]);
            questionHintText = lessonTemplateEmpty[questionCardHintIndexMarker];
            allowToStore[1] = false;
        }
        if (answerText.isEmpty() || answerText.equals(lessonTemplateEmpty[answerCardIndex])) {
            answerTextField.setText(lessonTemplateEmpty[answerCardIndex]);
            answerText = lessonTemplateEmpty[answerCardIndex];
            allowToStore[2] = false;
        }
        if (allowToStore[0] || allowToStore[2]) {
            allowToStoreCard = true;
        } else {
            allowToStoreCard = false;
        }
        if (allowToStoreCard) {
            if (!makeNewCard) {
                firstCardInRowTempEdit[Arrays.questionCardIndex] = questionText;
                firstCardInRowTempEdit[Arrays.questionCardHintIndexMarker] = questionHintText;
                firstCardInRowTempEdit[Arrays.answerCardIndex] = answerText;
                firstCardInRowTempEdit[Arrays.answerCardHintIndexMarker] = answerHintText;
                FilesWriteLessons.writeFileWords(activity, loadedCardsAsArray);
                String[][] tempArray = Arrays.sortLesson(loadedCardsAsArray);
                POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArray, lessonNames[lessonIndex]);
                try {
                    poiStoreExcelFile.writeToExcel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity().getApplicationContext(), StringsUI.storedEditedCardText, Toast.LENGTH_SHORT).show();
            } else {
                if (!allowToAddNewCard) {
                    firstCardInRowTempEdit[Arrays.questionCardIndex] = question;
                    firstCardInRowTempEdit[Arrays.questionCardHintIndexMarker] = questionHint;
                    firstCardInRowTempEdit[Arrays.answerCardIndex] = answer;
                    firstCardInRowTempEdit[Arrays.answerCardHintIndexMarker] = answerHint;

                    firstCardInRowTempEdit[Arrays.allowReverseCardIndexQuestionMarker] = checkBoxReverse.isChecked() ? "true" : "false";
                    firstCardInRowTempEdit[Arrays.showHintIndexMarker] = checkBoxShowHint.isChecked() ? "true" : "false";
                    loadedCardsAsArray[editedCardIndex] = firstCardInRowTempEdit;
                    FilesWriteLessons.writeFileWords(activity, loadedCardsAsArray);
                    loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
                    String[][] tempArray = Arrays.sortLesson(loadedCardsAsArray);
                    POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArray, lessonNames[lessonIndex]);
                    try {
                        poiStoreExcelFile.writeToExcel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getActivity().getApplicationContext(), StringsUI.storedNewCardText, Toast.LENGTH_SHORT).show();

                } else {
                    int newSize = loadedCardsAsArray.length + 1;
                    String[] tempCard = new String[lessonsTemplateCardMetaInfoArrayMinLength + 1];
                    tempCard[Arrays.questionCardIndex] = questionTextField.getText().toString();
                    tempCard[Arrays.questionCardHintIndexMarker] = questionHintTextField.getText().toString();
                    tempCard[Arrays.answerCardIndex] = answerTextField.getText().toString();
                    tempCard[Arrays.answerCardHintIndexMarker] = "#";

                    tempCard[Arrays.allowReverseCardIndexQuestionMarker] = "" + allowToReverseCard;
                    tempCard[Arrays.showHintIndexMarker] = "" + showHint;
                    tempCard[questionCardRepeatIndexMarker] = lessonTemplateEmpty[questionCardRepeatIndexMarker];
                    tempCard[answerCardRepeatIndexMarker] = lessonTemplateEmpty[answerCardRepeatIndexMarker];
                    tempCard[questionRepeatTimeIndexMarker] = lessonTemplateEmpty[questionRepeatTimeIndexMarker];
                    tempCard[answerRepeatTimeIndexMarker] = lessonTemplateEmpty[answerRepeatTimeIndexMarker];

                    tempCard[questionTimeStepIndexMarker] = lessonTemplateEmpty[questionTimeStepIndexMarker];
                    tempCard[answerTimeStepIndexMarker] = lessonTemplateEmpty[answerTimeStepIndexMarker];
                    tempCard[questionCountRightIndexMarker] = lessonTemplateEmpty[questionCountRightIndexMarker];
                    tempCard[questionCountFalseIndexMarker] = lessonTemplateEmpty[questionCountFalseIndexMarker];
                    tempCard[answerCountFalseIndexMarker] = lessonTemplateEmpty[answerCountFalseIndexMarker];
                    tempCard[answerCountRightIndexMarker] = lessonTemplateEmpty[answerCountRightIndexMarker];
                    tempCard[sortIndexCard] = "" + newSize;
                    tempCard[sortIndexCardProgress] = lessonTemplateEmpty[sortIndexCardProgress];
                    tempCard[fileInfoII] = lessonTemplateEmpty[fileInfoII];

                    String[][] tempArrayForNewCard = loadedCardsAsArray;
                    String[][] tempArray = new String[newSize][lessonsTemplateCardMetaInfoArrayMinLength];
                    tempArray[0] = tempCard;
                    for (int i = 1; i < newSize; i++) {
                        tempArray[i] = tempArrayForNewCard[i - 1];
                    }
                    loadedCardsAsArray = tempArray;
                    FilesWriteLessons.writeFileWords(activity, loadedCardsAsArray);
                    tempArrayForNewCard = Arrays.sortLesson(tempArray);
                    POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArrayForNewCard, lessonNames[lessonIndex].toString());
                    try {
                        poiStoreExcelFile.writeToExcel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getActivity().getApplicationContext(), StringsUI.storedNewCardText + " ", Toast.LENGTH_SHORT).show();

            }

            title.setText(StringsUI.menuDialogEdit);
        } else {
            Toast.makeText(getActivity().getApplicationContext(), StringsUI.storeNewCardNotAllowedText + " ", Toast.LENGTH_SHORT).show();
        }
        makeNewCard = false;
    }

    private void checkCard(String questionText, String questionHintText, String answerText) {
        boolean[] allowToStore = {true, true, true};
        if (questionText.isEmpty() || questionText.equals(lessonTemplateEmpty[questionCardIndex])) {
            questionTextField.setText(lessonTemplateEmpty[questionCardIndex]);
            questionText = lessonTemplateEmpty[questionCardIndex];
            allowToStore[0] = false;
        }
        if (questionHintText.isEmpty() || questionHintText.equals(lessonTemplateEmpty[questionCardHintIndexMarker])) {
            questionHintTextField.setText(lessonTemplateEmpty[questionCardHintIndexMarker]);
            questionHintText = lessonTemplateEmpty[questionCardHintIndexMarker];
            // This boolean must not be set. It is okay like that.
//                allowToStoreCard = false;
        }
        if (answerText.isEmpty() || answerText.equals(lessonTemplateEmpty[answerCardIndex])) {
            answerTextField.setText(lessonTemplateEmpty[answerCardIndex]);
            answerText = lessonTemplateEmpty[answerCardIndex];
            allowToStore[1] = false;
        }
//            if (answerHintText.isEmpty() || answerHintText.equals(lessonTemplateEmpty[answerCardHintIndexMarker])) {
//                answerHintTextField.setText(lessonTemplateEmpty[answerCardHintIndexMarker]);
//                allowToStoreCard = false;
//            }
        if (allowToStore[0] || allowToStore[1]) {
            allowToStoreCard = true;
        }
    }

    public void storeCard(TextView questionTextField, TextView questionHintTextField, TextView answerTextField) {
        String questionText = questionTextField.getText().toString();
        String questionHintText = questionHintTextField.getText().toString();
        String answerText = answerTextField.getText().toString();
        questionText = removeRegex(questionText);
        questionHintText = removeRegex(questionHintText);
        answerText = removeRegex(answerText);
        String answerHintText = "#";
        allowToStoreCard = true;
        boolean[] allowToStore = {true, true, true};
        if (questionText.isEmpty() || questionText.equals(lessonTemplateEmpty[questionCardIndex])) {
            questionTextField.setText(lessonTemplateEmpty[questionCardIndex]);
            questionText = lessonTemplateEmpty[questionCardIndex];
            allowToStore[0] = false;
        }
        if (questionHintText.isEmpty() || questionHintText.equals(lessonTemplateEmpty[questionCardHintIndexMarker])) {
            questionHintTextField.setText(lessonTemplateEmpty[questionCardHintIndexMarker]);
            questionHintText = lessonTemplateEmpty[questionCardHintIndexMarker];
            // This boolean must not be set. It is okay like that.
//                allowToStoreCard = false;
        }
        if (answerText.isEmpty() || answerText.equals(lessonTemplateEmpty[answerCardIndex])) {
            answerTextField.setText(lessonTemplateEmpty[answerCardIndex]);
            answerText = lessonTemplateEmpty[answerCardIndex];
            allowToStore[1] = false;
        }
//            if (answerHintText.isEmpty() || answerHintText.equals(lessonTemplateEmpty[answerCardHintIndexMarker])) {
//                answerHintTextField.setText(lessonTemplateEmpty[answerCardHintIndexMarker]);
//                allowToStoreCard = false;
//            }
        if (allowToStore[0] || allowToStore[1]) {
            allowToStoreCard = true;
        }
        if (allowToStoreCard)
            if (!makeNewCard) {
                // Edit existing card
                firstCardInRowTempEdit[Arrays.questionCardIndex] = questionText;
                firstCardInRowTempEdit[Arrays.questionCardHintIndexMarker] = questionHintText;
                firstCardInRowTempEdit[Arrays.answerCardIndex] = answerText;
                firstCardInRowTempEdit[Arrays.answerCardHintIndexMarker] = answerHintText;

                // Write to file and excel sheet
                FilesWriteLessons.writeFileWords(activity, loadedCardsAsArray);
                String[][] tempArray = Arrays.sortLesson(loadedCardsAsArray);
                POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArray, lessonNames[lessonIndex]);
                try {
                    poiStoreExcelFile.writeToExcel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity().getApplicationContext(), StringsUI.storedEditedCardText, Toast.LENGTH_SHORT).show();
            } else {
                // Add new card
                if (!allowToAddNewCard) {
                    firstCardInRowTempEdit[Arrays.questionCardIndex] = question;
                    firstCardInRowTempEdit[Arrays.questionCardHintIndexMarker] = questionHint;
                    firstCardInRowTempEdit[Arrays.answerCardIndex] = answer;
                    firstCardInRowTempEdit[Arrays.answerCardHintIndexMarker] = answerHint;
                    firstCardInRowTempEdit[Arrays.allowReverseCardIndexQuestionMarker] = checkBoxReverse.isChecked() ? "true" : "false";
                    firstCardInRowTempEdit[Arrays.showHintIndexMarker] = checkBoxShowHint.isChecked() ? "true" : "false";
                    loadedCardsAsArray[editedCardIndex] = firstCardInRowTempEdit;
                    FilesWriteLessons.writeFileWords(activity, loadedCardsAsArray
                    );
// Refresh the ListView
//                    MyAdapter adapter = (MyAdapter) myListView.getAdapter();
//                    adapter.notifyDataSetChanged();
                    Toast.makeText(getActivity().getApplicationContext(), StringsUI.storedNewCardText, Toast.LENGTH_SHORT).show();
                } else {
// Show message for not allowing to add new card
                    Toast.makeText(getActivity().getApplicationContext(), StringsUI.storeNewCardNotAllowedText, Toast.LENGTH_SHORT).show();
                }

            }


// Show message for invalid card
        Toast.makeText(getActivity().getApplicationContext(), StringsUI.storeNewCardNotAllowedText, Toast.LENGTH_SHORT).show();

    }



//    public void removeCardLogicFromEditMenu() {
//        String[][] tempArrayForDelete = loadedCardsAsArray;
//        int remove = Integer.parseInt(tempArrayForDelete[editedCardIndex][sortIndexCard]);
//        tempArrayForDelete = Arrays.sortLessonDelete(tempArrayForDelete, remove);
//        LinkedList<LinkedList<String>> tempList = Arrays.readArrayToList(loadedCardsAsArray);
//        tempList.remove(editedCardIndex);
//        tempArrayForDelete = Arrays.readListToArray(tempList);
//        LinkedList loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
//        loadedCardsAsArray = Arrays.sortLessonDelete(loadedCardsAsArray, remove);
//        loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
//        loadedCardsAsList.remove(remove);
//        loadedCardsAsArray = Arrays.readListToArray(loadedCardsAsList);
//        Arrays.cardlistLenght = loadedCardsAsArray.length;
//        if (editedCardIndex > Arrays.cardlistLenght - 1) {
//            editedCardIndex = Arrays.cardlistLenght - 1;
//        }
//        FilesWriteLessons.writeFileWords(getActivity().getApplicationContext(), tempArrayForDelete);
//
//
////        try {
//        //        POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArray, lessonNames[lessonIndex]);
//////            poiStoreExcelFile.writeToExcel();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }


    private boolean isFieldEmptyOrEqualToDefault(String fieldValue, String defaultValue, TextView textView) {
        if (fieldValue.isEmpty() || fieldValue.equals(defaultValue)) {
            textView.setText(defaultValue);
            return true;
        }
        return false;
    }


}
