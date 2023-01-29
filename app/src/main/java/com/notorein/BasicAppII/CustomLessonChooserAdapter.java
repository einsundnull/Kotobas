package com.notorein.BasicAppII;

import static com.notorein.BasicAppII.Colors.btn_text_color;
import static com.notorein.BasicAppII.Parameter.Parameter.mode;
import static com.notorein.BasicAppII.UISettingsItems.createDrawable;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Files.FilesReadSettings;
import com.notorein.BasicAppII.Parameter.Parameter;


public class
CustomLessonChooserAdapter extends BaseAdapter {


    Context c;
    public static LayoutInflater inflater;
    String[] lessonFilesList;

    CustomLessonChooserAdapter(Context context, Activity activity, String[] lessonFiles) {
        this.c = context;
        FilesReadSettings.scanLessonSettingsFile(activity);
        this.lessonFilesList = lessonFiles;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lessonFilesList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_item, null);
        TextView txtView = view.findViewById(R.id.listViewLessonsItemTextView);
        txtView.setWidth((int) (Parameter.displayWidth * 0.9f));
//        txtView.setBackground(UISettingsItems.createDrawable(Color.DKGRAY, Color.GRAY, 3, 1));
//        if (mode == 0) {
//            txtView.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.background, 0, 0));
//            txtView.setTextColor(Colors.btn_text_color);
//        } else {
//            txtView.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.btn_text_color, 0, 0));
//
//        }
        String txtViewText = lessonFilesList[i];
        txtViewText = txtViewText.replace("_", " ");
        txtView.setText(txtViewText);
        // These lines set the color of the selected item so one can see what is selected
//        txtView.setBackgroundColor(Colors.background);
//        txtView.setBackground(UISettingsItems.createDrawable(Color.YELLOW, Color.LTGRAY, 5, 3));
//        txtView.setTextColor(Colors.textNonSelected);
        txtView.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.background, 0, 0));
        txtView.setTextColor(btn_text_color);
        if (i == Arrays.lessonIndex) {
            txtView = view.findViewById(R.id.listViewLessonsItemTextView);
            if (mode != 0) {
                txtView.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.background, 0, 0));
                txtView.setTextColor(btn_text_color);
            }
            else {
                txtView.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.background, 0, 4));
                txtView.setTextColor(btn_text_color);
            }
        }
        return view;

    }
}
