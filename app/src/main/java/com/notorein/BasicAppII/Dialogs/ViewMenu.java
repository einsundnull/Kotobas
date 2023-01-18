package com.notorein.BasicAppII.Dialogs;

import static com.notorein.BasicAppII.Parameter.Parameter.displayHeight;
import static com.notorein.BasicAppII.Parameter.Parameter.displayWidth;
import static com.notorein.BasicAppII.UISettingsItems.createDrawable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.ActivityAbout;
import com.notorein.BasicAppII.ActivityCards;
import com.notorein.BasicAppII.ActivityLessons;
import com.notorein.BasicAppII.ActivitySettings;
import com.notorein.BasicAppII.Colors;
import com.notorein.BasicAppII.Strings.StringsUI;


public class ViewMenu extends Dialog implements View.OnClickListener {


    private final int layoutWidth;
    private int layoutHeight;
    private final int mode;
    public ConstraintLayout layout;
    public TextView title;
    public TextView btn_show_lessons;
    public TextView btn_show_cards;
    public TextView btn_settings;
    public TextView btn_about;
    public TextView btn_manual;
    Activity activity;
    Context context;
    Button btnLeft;
    Button btnRight;
    TextView questionTxtVw;
    TextView answerTxtVw;


    public ViewMenu(Context context, Activity activity, int mode, ConstraintLayout layout) {
        super(activity);
        this.context = context;
        this.mode = mode;
        this.activity = activity;
        this.layout = layout;
        layoutWidth = (int) (displayWidth * 0.99);
        layoutHeight = (int) (displayHeight);
    }


    public Activity getActivity() {
        return activity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_menu);
        layout = findViewById(R.id.menuLayout);
        title = layout.findViewById(R.id.menuTitle);
        btn_show_lessons = layout.findViewById(R.id.btn_show_lessons);
        btn_show_cards = layout.findViewById(R.id.btn_show_cards);
        btn_settings = layout.findViewById(R.id.btn_settings);
        btn_manual = layout.findViewById(R.id.btn_manual);
        btn_about = layout.findViewById(R.id.btn_about);

        layout.setMinWidth((int) (layoutWidth * 0.99));
//        layout.setMinHeight((int) layoutHeight);
        title.setMinWidth((int) (layoutWidth * 0.9));
        btn_show_lessons.setMinWidth((int) (layoutWidth * 0.9));
        btn_show_cards.setMinWidth((int) (layoutWidth * 0.9));
        btn_settings.setMinWidth((int) (layoutWidth * 0.9));
        btn_about.setMinWidth((int) (layoutWidth * 0.9));
        btn_manual.setMinWidth((int) (layoutWidth * 0.9));

        title.setText(StringsUI.menuDialog);
        btn_show_lessons.setText(StringsUI.menuDialogs[0]);
        btn_show_cards.setText(StringsUI.menuDialogs[1]);
        btn_settings.setText(StringsUI.menuDialogs[2]);
        btn_manual.setText(StringsUI.menuDialogs[3]);
        btn_about.setText(StringsUI.menuDialogs[4]);

        btn_show_lessons.setOnClickListener(this);
        btn_show_cards.setOnClickListener(this);
        btn_settings.setOnClickListener(this);
        btn_manual.setOnClickListener(this);
        btn_about.setOnClickListener(this);
        setModeColors();
//        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//            btn_show_cards.setEnabled(true);
//        } else {
//            btn_show_cards.setEnabled(false);
//        }

    }


    @Override
    public void onClick(View v) {
        boolean showAbout = true;
        Intent intent;
        if (v.getId() == R.id.btn_show_lessons) {
//            intent = new Intent(context, ActivitySettings.class);
//            context.startActivity(intent);
//            hide();

            intent = new Intent(context, ActivityLessons.class);
            context.startActivity(intent);
//            hide();
        }
        if (v.getId() == R.id.btn_show_cards) {
//            Parameter.tempButtonLayoutY = 0;
//            StringsUI.tempQuestionText = questionTxtVw.getText().toString();
//            btnLeft.setText(StringsUI.adjustText[0]);
//            btnRight.setText(StringsUI.adjustText[1]);
//            questionTxtVw.setText(StringsUI.adjustText[2]);
//            try {
//
//            } catch (Exception e) {
//                adaptTextViewTextSize(findViewById(R.id.questionTxtVw), Parameter.questionTextSizePercentage);
//            }
//
//
////            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            Parameter.moveButtons = true;
//            hide();
            intent = new Intent(context, ActivityCards.class);
            context.startActivity(intent);
//            hide();
        }
        if (v.getId() == R.id.btn_settings) {
            intent = new Intent(context, ActivitySettings.class);
            context.startActivity(intent);
            hide();
        }
        if (v.getId() == R.id.btn_manual) {
            showAbout = false;
            ViewManual manual = new ViewManual(activity);
            manual.show();
            hide();
        }
        if (v.getId() == R.id.btn_about) {
            if (showAbout) {
                intent = new Intent(context, ActivityAbout.class);
                context.startActivity(intent);
                hide();
            }
        }
    }


    public void setModeColors() {
        layout.setBackgroundColor(Colors.background);
//        title.setBackgroundColor(Colors.background);
//        settings.setBackgroundColor(Colors.btnDark);
//        adjust.setBackgroundColor(Colors.btnDark);
//        lessons.setBackgroundColor(Colors.btnDark);
//        manual.setBackgroundColor(Colors.btnDark);
//        about.setBackgroundColor(Colors.btnDark);

        title.setTextColor(Colors.btn_text_color);

//        layout.setBackgroundColor(Colors.background);
//        title.setBackground(createDrawable(Color.DKGRAY, Color.LTGRAY, 5, 3));
        if(mode == 0){
            btn_show_lessons.setBackground(createDrawable(Colors.btn_text_color, Colors.hint_text_color, 3, 0));
            btn_show_cards.setBackground(createDrawable(Colors.btn_text_color, Colors.hint_text_color, 3, 0));
            btn_settings.setBackground(createDrawable(Colors.btn_text_color, Colors.hint_text_color, 3, 0));
            btn_manual.setBackground(createDrawable(Colors.btn_text_color, Colors.hint_text_color, 3, 0));
            btn_about.setBackground(createDrawable(Colors.btn_text_color, Colors.hint_text_color, 3, 0));
            btn_show_lessons.setTextColor(Colors.btn_text_color);
            btn_show_cards.setTextColor(Colors.btn_text_color);
            btn_settings.setTextColor(Colors.btn_text_color);
            btn_manual.setTextColor(Colors.btn_text_color);
            btn_about.setTextColor(Colors.btn_text_color);
        } else {
            btn_show_lessons.setBackground(createDrawable(Colors.btn_text_color, Colors.btn_text_color, 3, 1));
            btn_show_cards.setBackground(createDrawable(Colors.btn_text_color, Colors.btn_text_color, 3, 1));
            btn_settings.setBackground(createDrawable(Colors.btn_text_color, Colors.btn_text_color, 3, 1));
            btn_manual.setBackground(createDrawable(Colors.btn_text_color, Colors.btn_text_color, 3, 1));
            btn_about.setBackground(createDrawable(Colors.btn_text_color, Colors.btn_text_color, 3, 1));
            btn_show_lessons.setTextColor(Colors.background);
            btn_show_cards.setTextColor(Colors.background);
            btn_settings.setTextColor(Colors.background);
            btn_manual.setTextColor(Colors.background);
            btn_about.setTextColor(Colors.background);
        }


    }


}
