package com.notorein.BasicAppII.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Arrays;
import com.notorein.BasicAppII.Colors;
import com.notorein.BasicAppII.POI.POIWriteNewEXCELFile;
import com.notorein.BasicAppII.Parameter.ParameterEXCEL;
import com.notorein.BasicAppII.Strings.StringsEXCEL;
import com.notorein.BasicAppII.Strings.StringsUI;
import com.notorein.BasicAppII.UISettingsItems;

public class Dialogs extends Dialog {
    private final Context context;
    public boolean actionSuccessFull;
    private Activity activity;
    private Dialog dialog;

    public Dialogs(@NonNull Context context, Activity activity) {
        super(context);
        this.activity = activity;
        this.context = context;
    }


    public void showStoreNewLessonDialog() {
        // Create a Dialog
        dialog = new Dialog(context);
        actionSuccessFull = false;

        // Set the layout for the Dialog
        dialog.setContentView(R.layout.dialog_store_new_lesson_layout);

        // Find all the views in the Dialog by their ids
        ConstraintLayout layout = dialog.findViewById(R.id.dialog_store_new_lesson_layout);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;
        int displayHeight = displayMetrics.heightPixels;
        // Set the width of the ConstraintLayout to 99% of the display width
//        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) layout.getLayoutParams();
        layout.setMinWidth((int) (displayWidth * 0.9));
        layout.setMinHeight((int) (displayHeight * 1));

        ImageView btn_add = layout.findViewById(R.id.btn_add);
        ImageView btn_close = layout.findViewById(R.id.btn_close);
        TextView title_new_lesson = layout.findViewById(R.id.title_new_lesson);


        EditText custom_edit_text_input = layout.findViewById(R.id.custom_edit_text_input);
        custom_edit_text_input.setHint(StringsUI.newLessonName);

        title_new_lesson.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        title_new_lesson.setTextColor(Colors.btn_text_color);

        // Set the background for each view
        layout.setBackground(UISettingsItems.createDrawable(Colors.background, Color.LTGRAY, 3, 1));
//        btn_add.setBackground(UISettingsItems.createDrawable(Color.BLACK, Color.LTGRAY, 3, 1));
        btn_add.setColorFilter(Colors.btn_text_color);
        btn_close.setColorFilter(Colors.btn_text_color);



//        ######################
//        editText.setBackground(UISettingsItems.createDrawable(Color.BLACK, Color.LTGRAY, 3, 1));
//        editText.setBackground(activity.getResources().getLayout(R.layout.custom_edit_text));
//          #########################################
        // Set an OnClickListener on the ImageView
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
                fadeInAnimation.setDuration(1000); // duration in milliseconds

// Set the animation listener
                fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        // Set the text color to green
                        btn_add.setBackground(UISettingsItems.createDrawable(Color.BLACK, Color.LTGRAY, 5, 5));

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // Do nothing
                    }
                });

                btn_add.startAnimation(fadeInAnimation);
                // When the ImageView is clicked, get the text from the EditText
                String text = custom_edit_text_input.getText().toString();
                String toastText = StringsUI.noNameForNewLessonSet;
                // If the text is not empty and is not "Your Lesson Name", call the storeNewLesson() method
                if (!text.isEmpty()) {
                    actionSuccessFull = POIWriteNewEXCELFile.storeNewLesson(activity, text, StringsEXCEL.lessonTemplateEmpty);
                    toastText = StringsUI.newLessonStoredTo + text + ParameterEXCEL.fileExtension;
                }
                // Show a Toast to confirm that the file was created
                Arrays.getAllLessonsInCustomFolder(context);
                Toast toast = Toast.makeText(activity, toastText, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.recreate();
                dialog.cancel();
            }
        });

        // Show the Dialog
        dialog.show();

    }

}
