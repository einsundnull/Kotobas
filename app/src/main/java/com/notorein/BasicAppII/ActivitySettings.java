package com.notorein.BasicAppII;


import static com.notorein.BasicAppII.Parameter.Parameter.mode;
import static com.notorein.BasicAppII.Parameter.Parameter.nightMode;
import static com.notorein.BasicAppII.Parameter.Parameter.playSoundWhenAnswerIsShown;
import static com.notorein.BasicAppII.Parameter.Parameter.playSoundWhenQuestionIsShown;
import static com.notorein.BasicAppII.Parameter.Parameter.saveBatteryMode;
import static com.notorein.BasicAppII.Parameter.Parameter.settingsWereUsed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Dialogs.ViewOptions;
import com.notorein.BasicAppII.Parameter.Parameter;
import com.notorein.BasicAppII.Strings.StringsUI;
import com.notorein.BasicAppII.Files.FilesReadSettings;
import com.notorein.BasicAppII.Files.FilesWriteLessons;
import com.notorein.BasicAppII.Files.FilesWriteSettings;

import java.io.File;

public class ActivitySettings extends AppCompatActivity implements View.OnClickListener {


    private ConstraintLayout questioningLayout;
    private UISettingsItems ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FilesReadSettings.scanLessonSettingsFile(this);
        FilesReadSettings.scanStartUpSettingsFile(this, this);
        setContentView(R.layout.layout_settings);
        questioningLayout = findViewById(R.id.settings_layout_id);
        ui = new UISettingsItems(questioningLayout);
        setLightMode();
        try {
            ui.textViewLessonName.setText(Arrays.lessonNames[Arrays.lessonIndex].replaceAll("_", " "));
            ui.textViewLessonName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ui.textViewLessonName.setTextSize(18);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTextViewOnCLick();
        setCheckBoxListener();
        setCheckBoxesChecked();
    }

    private void setCheckBoxListener() {

        ui.ckBxReverseQuestionRandom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ui.ckBxReverseQuestionEnd.setChecked(false);
                    ui.ckBxReverseQuestionCount.setChecked(false);
                    Parameter.advancedRepeatLogicIndex = 0;
                } else {
                    Parameter.advancedRepeatLogicIndex = 3;
                }
                FilesWriteSettings.writeStartupSettings(ActivitySettings.this);
            }
        });

        ui.ckBxReverseQuestionEnd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ui.ckBxReverseQuestionRandom.setChecked(false);
                    ui.ckBxReverseQuestionCount.setChecked(false);
                    Parameter.advancedRepeatLogicIndex = 1;
                } else {
                    Parameter.advancedRepeatLogicIndex = 3;
                }
                FilesWriteSettings.writeStartupSettings(ActivitySettings.this);
            }
        });

        ui.ckBxReverseQuestionCount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ui.ckBxReverseQuestionEnd.setChecked(false);
                    ui.ckBxReverseQuestionRandom.setChecked(false);
                    Parameter.advancedRepeatLogicIndex = 2;
                } else {
                    Parameter.advancedRepeatLogicIndex = 3;
                }
                FilesWriteSettings.writeStartupSettings(ActivitySettings.this);
            }
        });
    }

    private void setCheckBoxesChecked() {

        if (Parameter.advancedRepeatLogicIndex == 0) {
            ui.ckBxReverseQuestionRandom.setChecked(true);
        } else if (Parameter.advancedRepeatLogicIndex == 1) {
            ui.ckBxReverseQuestionEnd.setChecked(true);
        } else if (Parameter.advancedRepeatLogicIndex == 2) {
            ui.ckBxReverseQuestionCount.setChecked(true);
        } else if (Parameter.advancedRepeatLogicIndex == 3) {
            ui.ckBxReverseQuestionCount.setChecked(false);
            ui.ckBxReverseQuestionEnd.setChecked(false);
            ui.ckBxReverseQuestionRandom.setChecked(false);
        }
    }

    private void setLightMode() {
        if (nightMode || saveBatteryMode) {
            if (nightMode) {
//                ui.setNightMode(this);
            } else {
//                ui.setBatteryMode(this);
            }
        } else {
//            ui.setDayMode(this);
        }
    }

    public void setTextViewOnCLick() {

        ui.ckBxShowAnswerWithQuestion.setOnClickListener(this);
        ui.ckBxNightMode.setOnClickListener(this);
        ui.ckBxReverseQuestion.setOnClickListener(this);
        ui.ckBxEnergyMode.setOnClickListener(this);
        ui.ckBxSoundQuestion.setOnClickListener(this);
        ui.ckBxSoundAnswer.setOnClickListener(this);

        ui.ckBxReverseQuestionEnd.setOnClickListener(this);
        ui.ckBxReverseQuestionRandom.setOnClickListener(this);
        ui.ckBxReverseQuestionCount.setOnClickListener(this);

        ui.btnMix.setOnClickListener(this);
        ui.btnReset.setOnClickListener(this);
        ui.btnContLesson.setOnClickListener(this);
        ui.btnChooseLesson.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        AnswerLogic.answerWasShownWhenDestroy = false;
        AnswerLogic.answerIsVisible = false;
        settingsWereUsed = true;
        FilesWriteSettings.writeLessonSettings(this);
        FilesWriteSettings.writeStartupSettings(this);
        FilesWriteLessons.writeFileWords(this, Arrays.loadedCardsAsArray);
        Intent intent = new Intent(this, ActivityMain.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ckBxShowAnswerWithQuestion) {
            AnswerLogic.alwaysShowQuestionWithAnswer = ui.ckBxShowAnswerWithQuestion.isChecked();
            FilesWriteSettings.writeLessonSettings(this);
        }

        if (view.getId() == R.id.ckBxNightMode) {
            nightMode = !nightMode;
            saveBatteryMode = false;
            ui.ckBxEnergyMode.setSelected(false);
            setColorInMain();
            FilesWriteSettings.writeLessonSettings(this);
            FilesWriteSettings.writeStartupSettings(this);
            Intent intent = new Intent(ActivitySettings.this, ActivitySettings.class);
            startActivity(intent);
            finish();
        }

        if (view.getId() == R.id.ckBxBatteryMode) {
            saveBatteryMode = !saveBatteryMode;
            nightMode = false;
            ui.ckBxNightMode.setSelected(false);
            setColorInMain();
            FilesWriteSettings.writeLessonSettings(this);
            FilesWriteSettings.writeStartupSettings(this);
            Intent intent = new Intent(ActivitySettings.this, ActivitySettings.class);
            startActivity(intent);
            finish();
        }

        if (view.getId() == R.id.ckBxReverseQuestion) {
            AnswerLogic.questionIsReversed = !AnswerLogic.questionIsReversed;
            FilesWriteSettings.writeLessonSettings(this);
            FilesWriteSettings.writeStartupSettings(this);
        }

        if (view.getId() == R.id.ckBxSoundQuestion) {
            playSoundWhenQuestionIsShown = !playSoundWhenQuestionIsShown;
            FilesWriteSettings.writeLessonSettings(this);
        }

        if (view.getId() == R.id.ckBxSoundAnswer) {
            playSoundWhenAnswerIsShown = !playSoundWhenAnswerIsShown;
            FilesWriteSettings.writeLessonSettings(this);
        }

        if (view.getId() == R.id.btnMix) {
            ViewOptions options = new ViewOptions(this, mode, StringsUI.mixCards, StringsUI.toastTextMix, () -> {
                Arrays.mixCards();
                FilesWriteLessons.writeFileWords(ActivitySettings.this, Arrays.loadedCardsAsArray);
            });
            options.show();
        }

        if (view.getId() == R.id.btnReset) {
            ViewOptions options = new ViewOptions(this, mode, StringsUI.resetLesson, StringsUI.toastTextReset, () -> {
//                Arrays.sortLesson();
//                Arrays.resetLesson();
//                Arrays.readArrayToList();
//                FilesWriteLessons.writeFileWords(ActivitySettings.this);
                File file = new File(this.getFilesDir(), Arrays.lessonNames[Arrays.lessonIndex].replace(".xlsx", ""));
                if (file.exists()) {
                    file.delete();
//                  Toast.makeText(this,file.toString(),Toast.LENGTH_SHORT).show();

                } else {
//                  Toast.makeText(this,"FILE NOT EXIST",Toast.LENGTH_SHORT).show();
//                  Toast.makeText(this,file.toString(),Toast.LENGTH_SHORT).show();
                }
            });
            options.show();
        }

        if (view.getId() == R.id.btnChooseLesson) {
            FilesWriteSettings.writeLessonSettings(this);
            Intent intent = new Intent(ActivitySettings.this, ActivityLessons.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btnContLesson) {
//            AnswerLogic.answerWasShownWhenDestroy = false;
            AnswerLogic.answerIsVisible = false;
            settingsWereUsed = true;
//            Arrays.moveCardsInArrayAfterAnswer(activity, layout);
            FilesWriteSettings.writeLessonSettings(this);
            Intent intent = new Intent(ActivitySettings.this, ActivityMain.class);
            startActivity(intent);
        }
    }

    private void setColorInMain() {
        if (!nightMode && !saveBatteryMode) {
            mode = 0;
        } else {
            if (saveBatteryMode) {
                mode = 1;
            } else {
                mode = 2;
            }
        }
        Colors.setColorMode(this, mode);
    }
}