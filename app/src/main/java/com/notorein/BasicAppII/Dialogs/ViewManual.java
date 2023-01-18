package com.notorein.BasicAppII.Dialogs;

import static com.notorein.BasicAppII.Parameter.Parameter.alwaysShowManualAtStart;
import static com.notorein.BasicAppII.Parameter.Parameter.displayHeight;
import static com.notorein.BasicAppII.Parameter.Parameter.displayWidth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Colors;
import com.notorein.BasicAppII.Strings.StringsUI;
import com.notorein.BasicAppII.Files.FilesReadSettings;
import com.notorein.BasicAppII.Files.FilesWriteSettings;

public class ViewManual extends Dialog implements View.OnClickListener {



    private final int layoutWidth;
    private final int layoutHeight;
    public ConstraintLayout layout;

    private final Activity activity;
    private final Context c;
    private TextView title;
    private TextView text;
    private Button okayBtn;
    private CheckBox ckBxAlwaysShowManual;


    public ViewManual(Activity activity) {
        super(activity);
        this.c = activity;
        this.activity = activity;
        layoutWidth = (int) (displayWidth * 0.95);
        layoutHeight = (int) (displayHeight * 0.95);
        try {
            FilesReadSettings.scanLessonSettingsFile(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Activity getActivity() {
        return activity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_manual);

        layout = findViewById(R.id.manualLayout);
        title = findViewById(R.id.manualTitle);
        text =  findViewById(R.id.manualText);
        okayBtn =  findViewById(R.id.okayBtn);
        ckBxAlwaysShowManual =  findViewById(R.id.ckBxAlwaysShowManual);
        title.setText(StringsUI.manual);
        text.setText(StringsUI.openingText);
        ckBxAlwaysShowManual.setText(StringsUI.alwaysShowManual);
        try {
            FilesReadSettings.scanLessonSettingsFile(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ckBxAlwaysShowManual.setSelected(alwaysShowManualAtStart);
        layout.setMinWidth((int) (layoutWidth * 0.9));
        layout.setMaxHeight(layoutHeight);
        okayBtn.setMinWidth((int) (layoutWidth * 0.9));
//        okayBtn.setTextColor(c.getResources().getColor(R.color.day_background_paper));
//        okayBtn.setBackgroundColor(c.getResources().getColor(R.color.energy_save_button_text));
        okayBtn.setText(StringsUI.ok);
        okayBtn.setOnClickListener(this);
        setColors();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.okayBtn) {
            hide();
            FilesWriteSettings.writeLessonSettings(activity);
        }
        if (v.getId() == R.id.ckBxAlwaysShowManual) {
          alwaysShowManualAtStart = ckBxAlwaysShowManual.isSelected();
            FilesWriteSettings.writeLessonSettings(activity);
        }
    }

    private void setColors() {
        layout.setBackgroundColor(Colors.background);
        okayBtn.setBackgroundColor(Colors.btnStroke);
        okayBtn.setTextColor(Colors.textNonSelected);
        title.setTextColor(Colors.btn_text_color);
        text.setTextColor(Colors.btn_text_color);
    }

}
