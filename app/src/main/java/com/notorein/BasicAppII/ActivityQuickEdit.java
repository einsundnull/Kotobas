package com.notorein.BasicAppII;

import static com.notorein.BasicAppII.AnswerLogic.showHint;
import static com.notorein.BasicAppII.Arrays.answerCardHintIndexMarker;
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
import static com.notorein.BasicAppII.Parameter.Parameter.allowToStoreCard;
import static com.notorein.BasicAppII.Parameter.Parameter.editTextIndex;
import static com.notorein.BasicAppII.Parameter.Parameter.mode;
import static com.notorein.BasicAppII.Parameter.Parameter.text;
import static com.notorein.BasicAppII.Strings.StringsEXCEL.lessonTemplateEmpty;
import static com.notorein.BasicAppII.Strings.StringsEXCEL.removeRegex;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Dialogs.ViewOptions;
import com.notorein.BasicAppII.Files.FilesWriteLessons;
import com.notorein.BasicAppII.POI.POIStoreExcelFile;
import com.notorein.BasicAppII.Parameter.Parameter;
import com.notorein.BasicAppII.Strings.StringsEXCEL;
import com.notorein.BasicAppII.Strings.StringsUI;

import java.io.IOException;

public class ActivityQuickEdit extends AppCompatActivity {

    private EditText questionInput;
    private EditText answerInput;
//    boolean cardHasBeenEdited;
//    private boolean allowToStoreCard;
//    public static  String[] firstCardInRowTemp;

    public static String answer;
    public static String answerHint;
    public static String question;
    public static String questionHint;
    //    private boolean makeNewCard = true;
    private boolean allowToAddNewCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_edit);
        ConstraintLayout layout = this.findViewById(R.id.layout_quick_edit);
        ImageView copyQ = findViewById(R.id.copyQ);
        ImageView pasteQ = findViewById(R.id.pasteQ);
        ImageView clearQ = findViewById(R.id.clearQ);
        ImageView btn_store_card = layout.findViewById(R.id.btn_store_card);
        ImageView btn_close = layout.findViewById(R.id.btn_close);
        questionInput = findViewById(R.id.questionInput);
        questionInput.setText(StringsEXCEL.QUESTION);
        answerInput = findViewById(R.id.answerInput);
        answerInput.setText(StringsEXCEL.ANSWER);

        btn_store_card.setOnClickListener(c -> {

        });

        btn_close.setOnClickListener(c -> {

            if (Parameter.cardHasBeenEdited) {
                ViewOptions options = new ViewOptions(this, mode, StringsUI.leaveWithoutSaving, "", () -> {
                    finish();
                });
                options.show();
            } else {
                finish();
            }
        });

        copyQ.setOnClickListener(c -> {
            copyLogic(questionInput);
        });

        pasteQ.setOnClickListener(c -> {
            questionInput = pasteLogic(questionInput);
        });

        clearQ.setOnClickListener(c -> {
            questionInput.setText("");
        });


        ImageView copyA = findViewById(R.id.copyA);
        ImageView pasteA = findViewById(R.id.pasteA);
        ImageView clearA = findViewById(R.id.clearA);
        answerInput = findViewById(R.id.answerInput);
        answerInput.setText(StringsEXCEL.ANSWER);

        copyA.setOnClickListener(c -> {
            copyLogic(answerInput);
        });

        pasteA.setOnClickListener(c -> {
            answerInput = pasteLogic(answerInput);
        });

        clearA.setOnClickListener(c -> {
            answerInput.setText("");
        });

        questionInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionInput.requestFocus();
                editTextIndex = 1;
                text = questionInput.getText().toString();
//                questionTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                questionHintTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                answerTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                answerHintTextField.setBackground(createDrawable(Color.YELLOW, Colors.background, 5, 3));
            }
        });

        answerInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerInput.requestFocus();
                editTextIndex = 2;
                text = answerInput.getText().toString();

//                questionTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                questionHintTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
//                answerTextField.setBackground(createDrawable(Color.YELLOW, Colors.background, 5, 3));
//                answerHintTextField.setBackground(createDrawable(Color.DKGRAY, Colors.background, 5, 3));
            }
        });
    }

    private void copyLogic(EditText editText) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String text = editText.getText().toString();
        ClipData clip = ClipData.newPlainText(StringsUI.clipboardLabelText, text);
        clipboard.setPrimaryClip(clip);
    }

    private EditText pasteLogic(EditText editText) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard.hasText()) {
            editText.setText(clipboard.getText());
        }
        return editText;
    }

    public void storeLogic(TextView questionTextField, TextView questionHintTextField, TextView answerTextField) {
        boolean[] allowToStore = {true, true, true};

        String questionText = questionTextField.getText().toString();
//            String questionHintText = questionHintTextField.getText().toString();
        String questionHintText = "#";
        String answerText = answerTextField.getText().toString();
//            String answerHintText = answerHintTextField.getText().toString();
        String answerHintText = "#";

        questionText = removeRegex(questionText);
        questionHintText = removeRegex(questionHintText);
        answerText = removeRegex(answerText);
        allowToStoreCard = true;

        if (questionText.isEmpty() || questionText.equals(lessonTemplateEmpty[questionCardIndex])) {
            questionTextField.setText(lessonTemplateEmpty[questionCardIndex]);
            questionText = lessonTemplateEmpty[questionCardIndex];
            allowToStoreCard = false;
            allowToStore[0] = false;
        }
//            if (questionHintText.isEmpty() || questionHintText.equals(lessonTemplateEmpty[questionCardHintIndexMarker])) {
//                questionHintTextField.setText(lessonTemplateEmpty[questionCardHintIndexMarker]);
        questionHintText = lessonTemplateEmpty[questionCardHintIndexMarker];
        // This boolean must not be set. It is okay like that.
//                allowToStoreCard = false;
//            }
        if (answerText.isEmpty() || answerText.equals(lessonTemplateEmpty[answerCardIndex])) {
            answerTextField.setText(lessonTemplateEmpty[answerCardIndex]);
            answerText = lessonTemplateEmpty[answerCardIndex];
            allowToStoreCard = false;
            allowToStore[1] = false;
        }
//            if (answerHintText.isEmpty() || answerHintText.equals(lessonTemplateEmpty[answerCardHintIndexMarker])) {
//                answerHintTextField.setText(lessonTemplateEmpty[answerCardHintIndexMarker]);
        answerHintText = lessonTemplateEmpty[answerCardHintIndexMarker];
//                allowToStoreCard = false;
//            }
        if (allowToStore[0] || allowToStore[1]) {
            allowToStoreCard = true;
        }
        if (allowToStoreCard) {
            if (!Parameter.makeNewCard) {
                Arrays.firstCardInRowTempEdit[Arrays.questionCardIndex] = questionText;
                Arrays.firstCardInRowTempEdit[Arrays.questionCardHintIndexMarker] = questionHintText;
                Arrays.firstCardInRowTempEdit[Arrays.answerCardIndex] = answerText;
                Arrays.firstCardInRowTempEdit[Arrays.answerCardHintIndexMarker] = answerHintText;

                FilesWriteLessons.writeFileWords(this, loadedCardsAsArray);
                String[][] tempArray = Arrays.sortLesson(loadedCardsAsArray);
                POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArray, lessonNames[lessonIndex]);
                try {
                    poiStoreExcelFile.writeToExcel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this.getApplicationContext(), StringsUI.storedEditedCardText, Toast.LENGTH_SHORT).show();

            } else {
                if (!allowToAddNewCard) {

                    firstCardInRowTempEdit[Arrays.questionCardIndex] = question;
                    firstCardInRowTempEdit[Arrays.questionCardHintIndexMarker] = questionHint;
                    firstCardInRowTempEdit[Arrays.answerCardIndex] = answer;
                    firstCardInRowTempEdit[Arrays.answerCardHintIndexMarker] = answerHint;

//                        firstCardInRowTemp[Arrays.allowReverseCardIndexQuestionMarker] = checkBoxReverse.isChecked() ? "true" : "false";
//                        firstCardInRowTemp[Arrays.showHintIndexMarker] = checkBoxShowHint.isChecked() ? "true" : "false";
                    loadedCardsAsArray[editedCardIndex] = firstCardInRowTempEdit;
                    FilesWriteLessons.writeFileWords(this, loadedCardsAsArray);
                    loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
                    String[][] tempArray = Arrays.sortLesson(loadedCardsAsArray);
                    POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArray, lessonNames[lessonIndex]);
                    try {
                        poiStoreExcelFile.writeToExcel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(this.getApplicationContext(), StringsUI.storedNewCardText, Toast.LENGTH_SHORT).show();

                } else {
                    int newSize = loadedCardsAsArray.length + 1;
                    String[] tempCard = new String[lessonsTemplateCardMetaInfoArrayMinLength + 1];
                    tempCard[Arrays.questionCardIndex] = questionTextField.getText().toString();
                    tempCard[Arrays.questionCardHintIndexMarker] = questionHintTextField.getText().toString();
                    tempCard[Arrays.answerCardIndex] = answerTextField.getText().toString();
//                        tempCard[Arrays.answerCardHintIndexMarker] = answerHintTextField.getText().toString();
                    tempCard[Arrays.answerCardHintIndexMarker] = "#";

                    String allowToReverseCard = "true";
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
                    FilesWriteLessons.writeFileWords(this, loadedCardsAsArray);
                    tempArrayForNewCard = Arrays.sortLesson(tempArray);
                    POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArrayForNewCard, lessonNames[lessonIndex].toString());
                    try {
                        poiStoreExcelFile.writeToExcel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(this.getApplicationContext(), StringsUI.storedNewCardText + " ", Toast.LENGTH_SHORT).show();

            }
            Parameter.makeNewCard = false;
//                title.setText(StringsUI.menuDialogEdit);
        } else {
            Toast.makeText(this.getApplicationContext(), StringsUI.storeNewCardNotAllowedText + " ", Toast.LENGTH_SHORT).show();
        }
    }
}