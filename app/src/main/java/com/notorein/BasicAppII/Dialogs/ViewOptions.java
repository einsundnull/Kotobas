package com.notorein.BasicAppII.Dialogs;

import static com.notorein.BasicAppII.Parameter.Parameter.displayWidth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Colors;
import com.notorein.BasicAppII.Strings.StringsUI;


@SuppressWarnings("deprecation")
public class ViewOptions extends Dialog implements View.OnClickListener {


    private final Runnable run;
    private final int mode;
    private final double layoutWidth;
    public ConstraintLayout layout;

    private final Activity activity;
    private final Context c;
    private TextView title;
    private TextView btnOkay;
    private TextView btnCancel;
    private final String text;
    private final String toastText;


    public ViewOptions(Activity activity, int mode, String text, String toastText, Runnable run) {
        super(activity);
        this.c = activity;
        layoutWidth = (displayWidth * 0.8);
        this.activity = activity;
        this.run = run;
        this.text = text;
        this.toastText = toastText;
        this.mode = mode;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_options);
        layout = findViewById(R.id.optionsLayout);
        title = findViewById(R.id.questionTextField);
        btnOkay = findViewById(R.id.btnOkay);
        btnCancel = findViewById(R.id.btnCancel);
        title.setText(text);
        int widthBtn = (int) (layoutWidth * 0.40);

        btnOkay.setMaxWidth(widthBtn);
        btnCancel.setMaxWidth(widthBtn);

//        title.setTextColor(c.getResources().getColor(R.color.day_background_paper));
//        title.setBackgroundColor(c.getResources().getColor(R.color.energy_save_button_text));
        title.setOnClickListener(this);

//        btnOkay.setTextColor(c.getResources().getColor(R.color.day_background_paper));
//        btnOkay.setBackgroundColor(c.getResources().getColor(R.color.energy_save_button_text));
        btnOkay.setText(StringsUI.ok);
        btnOkay.setOnClickListener(this);

//        btnCancel.setTextColor(c.getResources().getColor(R.color.day_background_paper));
//        btnCancel.setBackgroundColor(c.getResources().getColor(R.color.energy_save_button_text));
        btnCancel.setText(StringsUI.no);
        btnCancel.setOnClickListener(this);

        Colors.setColorMode(activity, mode);
        setModeColors();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnOkay) {
            run.run();
            hide();
            if (toastText != null) {
                Toast toast = new Toast(c);
                toast.setGravity(Gravity.CENTER, 0, 0);
                Toast.makeText(c, toastText, Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.btnCancel) {
            hide();
        }
    }

    public void setModeColors() {
        layout.setBackgroundColor(Colors.background);

        title.setBackgroundColor(Colors.background);
        btnOkay.setBackgroundColor(Colors.btnSolid);
        btnCancel.setBackgroundColor(Colors.btnSolid);

        title.setTextColor(Colors.btn_text_color);
        btnOkay.setTextColor(Colors.btn_text_color);
        btnCancel.setTextColor(Colors.btn_text_color);

    }


}
