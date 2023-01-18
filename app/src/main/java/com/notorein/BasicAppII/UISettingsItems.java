package com.notorein.BasicAppII;

import static com.notorein.BasicAppII.Parameter.Parameter.nightMode;
import static com.notorein.BasicAppII.Parameter.Parameter.playSoundWhenAnswerIsShown;
import static com.notorein.BasicAppII.Parameter.Parameter.playSoundWhenQuestionIsShown;
import static com.notorein.BasicAppII.Parameter.Parameter.saveBatteryMode;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;


public class UISettingsItems {

    public CheckBox ckBxShowAnswerWithQuestion;
    public CheckBox ckBxNightMode;
    public CheckBox ckBxEnergyMode;
    public CheckBox ckBxSoundQuestion;
    public CheckBox ckBxSoundAnswer;

    public CheckBox ckBxReverseQuestion;
    public CheckBox ckBxReverseQuestionEnd;
    public CheckBox ckBxReverseQuestionRandom;
    public CheckBox ckBxReverseQuestionCount;
    public TextView textViewLessonName;
    public ConstraintLayout layout;

    public Button btnMix;
    public Button btnReset;
    public Button btnContLesson;
    public Button btnChooseLesson;

//    int[][] states = {{R.color.day_ckbx_tint}, {R.color.energy_ckbx_tint}, {R.color.night_ckbx_tint}};

    public UISettingsItems(ConstraintLayout layout) {
        getViews(layout);
    }

    private void getViews(ConstraintLayout layout) {


        this.layout = layout.findViewById(R.id.settings_layout_id);
        textViewLessonName = layout.findViewById(R.id.textViewLessonName);
        ckBxShowAnswerWithQuestion = layout.findViewById(R.id.ckBxShowAnswerWithQuestion);
        ckBxReverseQuestion = layout.findViewById(R.id.ckBxReverseQuestion);
        ckBxNightMode = layout.findViewById(R.id.ckBxNightMode);
        ckBxEnergyMode = layout.findViewById(R.id.ckBxBatteryMode);
        btnMix = layout.findViewById(R.id.btnMix);
        btnReset = layout.findViewById(R.id.btnReset);
        btnContLesson = layout.findViewById(R.id.btnContLesson);
        btnChooseLesson = layout.findViewById(R.id.btnChooseLesson);
        ckBxSoundQuestion = layout.findViewById(R.id.ckBxSoundQuestion);
        ckBxSoundAnswer = layout.findViewById(R.id.ckBxSoundAnswer);
        ckBxReverseQuestionRandom = layout.findViewById(R.id.switchRandom );
        ckBxReverseQuestionEnd = layout.findViewById(R.id.switchLearned);

        ckBxReverseQuestionCount = layout.findViewById(R.id.switchCount);

        ckBxNightMode.setChecked(nightMode);
        ckBxEnergyMode.setChecked(saveBatteryMode);
        ckBxShowAnswerWithQuestion.setChecked(AnswerLogic.alwaysShowQuestionWithAnswer);
        ckBxReverseQuestion.setChecked(AnswerLogic.questionIsReversed);
        ckBxSoundQuestion.setChecked(playSoundWhenQuestionIsShown);
        ckBxSoundAnswer.setChecked(playSoundWhenAnswerIsShown);


        ckBxNightMode.setEnabled(true);


    }

//    public void setDayMode(Context c) {
//
//        ckBxShowAnswerWithQuestion.setTextColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        ckBxNightMode.setTextColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        ckBxEnergyMode.setTextColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        ckBxSoundQuestion.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        ckBxSoundAnswer.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//
//        ckBxReverseQuestion.setTextColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        ckBxReverseQuestionRandom.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        ckBxReverseQuestionCount.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        ckBxReverseQuestionEnd.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        textViewLessonName.setTextColor(c.getResources().getColor(R.color.white));
//
//        btnMix.setTextColor(c.getResources().getColor(R.color.white));
//        btnReset.setTextColor(c.getResources().getColor(R.color.white));
//        btnContLesson.setTextColor(c.getResources().getColor(R.color.white));
//        btnChooseLesson.setTextColor(c.getResources().getColor(R.color.white));
//
//        layout.setBackgroundColor(c.getResources().getColor(R.color.white));
//        ckBxShowAnswerWithQuestion.setBackgroundColor(c.getResources().getColor(R.color.white));
//        ckBxNightMode.setBackgroundColor(c.getResources().getColor(R.color.white));
//        ckBxEnergyMode.setBackgroundColor(c.getResources().getColor(R.color.white));
//        ckBxSoundQuestion.setBackgroundColor(c.getResources().getColor(R.color.white));
//        ckBxSoundAnswer.setBackgroundColor(c.getResources().getColor(R.color.white));
//
//        ckBxReverseQuestion.setBackgroundColor(c.getResources().getColor(R.color.white));
//        ckBxReverseQuestionRandom.setBackgroundColor(c.getResources().getColor(R.color.white));
//        ckBxReverseQuestionCount.setBackgroundColor(c.getResources().getColor(R.color.white));
//        ckBxReverseQuestionEnd.setBackgroundColor(c.getResources().getColor(R.color.white));
//
//        textViewLessonName.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//
//        btnMix.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        btnReset.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        btnContLesson.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        btnChooseLesson.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//
//    }
//
//    public void setBatteryMode(Context c) {
//
//        ckBxShowAnswerWithQuestion.setTextColor(c.getResources().getColor(R.color.white));
//        ckBxNightMode.setTextColor(c.getResources().getColor(R.color.white));
//        ckBxEnergyMode.setTextColor(c.getResources().getColor(R.color.white));
//        ckBxSoundQuestion.setTextColor(c.getResources().getColor(R.color.white));
//        ckBxSoundAnswer.setTextColor(c.getResources().getColor(R.color.white));
//        ckBxReverseQuestion.setTextColor(c.getResources().getColor(R.color.white));
//        ckBxReverseQuestionRandom.setTextColor(c.getResources().getColor(R.color.white));
//        ckBxReverseQuestionCount.setTextColor(c.getResources().getColor(R.color.white));
//        ckBxReverseQuestionEnd.setTextColor(c.getResources().getColor(R.color.white));
//        textViewLessonName.setTextColor(c.getResources().getColor(R.color.white));
//        btnMix.setTextColor(c.getResources().getColor(R.color.white));
//        btnReset.setTextColor(c.getResources().getColor(R.color.white));
//        btnContLesson.setTextColor(c.getResources().getColor(R.color.white));
//        btnChooseLesson.setTextColor(c.getResources().getColor(R.color.white));
//        layout.setBackgroundColor(c.getResources().getColor(R.color.black));
//
//        ckBxReverseQuestion.setBackgroundColor(c.getResources().getColor(R.color.black));
//        ckBxReverseQuestionRandom.setBackgroundColor(c.getResources().getColor(R.color.black));
//        ckBxReverseQuestionCount.setBackgroundColor(c.getResources().getColor(R.color.black));
//        ckBxReverseQuestionEnd.setBackgroundColor(c.getResources().getColor(R.color.black));
//
//        textViewLessonName.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//
//        btnMix.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        btnReset.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        btnContLesson.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//        btnChooseLesson.setBackgroundColor(c.getResources().getColor(R.color.day_text_dark_grey));
//    }
//
//    public void setNightMode(Context c) {
//
//        ckBxShowAnswerWithQuestion.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        ckBxNightMode.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        ckBxEnergyMode.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        ckBxSoundQuestion.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        ckBxSoundAnswer.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        layout.setBackgroundColor(c.getResources().getColor(R.color.black));
//        ckBxReverseQuestion.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        ckBxReverseQuestionRandom.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        ckBxReverseQuestionCount.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        ckBxReverseQuestionEnd.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        textViewLessonName.setTextColor(c.getResources().getColor(R.color.black));
//
//        btnMix.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        btnReset.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        btnContLesson.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//        btnChooseLesson.setTextColor(c.getResources().getColor(R.color.night_red_dark));
//
//        ckBxReverseQuestion.setBackgroundColor(c.getResources().getColor(R.color.black));
//        ckBxReverseQuestionRandom.setBackgroundColor(c.getResources().getColor(R.color.black));
//        ckBxReverseQuestionCount.setBackgroundColor(c.getResources().getColor(R.color.black));
//        ckBxReverseQuestionEnd.setBackgroundColor(c.getResources().getColor(R.color.black));
//        textViewLessonName.setBackgroundColor(c.getResources().getColor(R.color.night_red_dark));
//
//        btnMix.setBackgroundColor(c.getResources().getColor(R.color.night_button_background));
//        btnReset.setBackgroundColor(c.getResources().getColor(R.color.night_button_background));
//        btnContLesson.setBackgroundColor(c.getResources().getColor(R.color.night_button_background));
//        btnChooseLesson.setBackgroundColor(c.getResources().getColor(R.color.night_button_background));
//
//    }
    public static Drawable createDrawable(int strokeColor, int solidColor, int radius , int strokeWidth) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setColor(solidColor);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    public static void animateColorTransition(ImageView view) {
        // Define the colors to transition between
        int colorFrom = Color.GREEN;
        // Original Color
        int colorTo = Color.LTGRAY;

        // Create a color transition animator
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(1000); // Transition duration: 1 second
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }
        });

        // Start the animation
        colorAnimation.start();
    }

}
//.setBackground(UISettingsItems.createDrawable(Color.BLACK, Color.LTGRAY, 5, 5));