package com.notorein.BasicAppII;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.notorein.BasicApp.R;

public class Colors {

    public static int btnSolid;
    public static int btnStroke;
    public static int btn_text_color;
    public static int textBright;
    //    public static int textDark;
//    public static int inputTextColor;
    public static int textSelected;
    public static int btnSelected;
    public static int background;
    public static int backgroundSelected;
    public static int textAbout;
    public static int listViewDividerColor;
    public static int textNonSelected;
    public static int btnNonSelected;
    public static Drawable saveBackround;
    private static int btnBackgroundStroke;
    public static Drawable closeBackground;
    public static Drawable copyBackground;
    public static Drawable btnBackground;
    public static Drawable btn_edit_card_background;
    public static Drawable btn_add_card_background;
    public static int textListViewItemSelected;
    public static Drawable btn_menu_background;
    public static int btn_background_solid_day;
    public static int hint_text_color;
    public static Drawable deletBackground;
    public static Drawable swapCardQuestion;
    public static int btn_background_stroke_day_selected;


    public static void setColorMode(Activity c, int mode) {

        btn_background_stroke_day_selected = c.getResources().getColor(R.color.btn_background_stroke_day_selected);

        if (mode == 0) {
            background = c.getResources().getColor(R.color.background_color_day);
            btn_text_color = c.getResources().getColor(R.color.btn_background_text_color_day);
            hint_text_color = c.getResources().getColor(R.color.hint_text_color_day);
            btn_background_solid_day = c.getResources().getColor(R.color.btn_background_solid_day);
            btnBackground = c.getResources().getDrawable(R.drawable.custom_button_day);
            btnBackgroundStroke = c.getResources().getColor(R.color.btn_background_stroke_day);

            saveBackround = c.getResources().getDrawable(R.drawable.btn_save_card_background_day);
            copyBackground = c.getResources().getDrawable(R.drawable.btn_copy_card_background_day);
            deletBackground = c.getResources().getDrawable(R.drawable.btn_delete_card_background_day);
            swapCardQuestion = c.getResources().getDrawable(R.drawable.btn_swap_cards_background_day);
            closeBackground = c.getResources().getDrawable(R.drawable.btn_close_dialog_background_day);
            btn_menu_background = c.getResources().getDrawable(R.drawable.btn_menu_background_day);
            btn_edit_card_background = c.getResources().getDrawable(R.drawable.btn_edit_card_background_day);
            btn_add_card_background = c.getResources().getDrawable(R.drawable.btn_add_card_background_day);
        }
        if (mode == 1) {
            swapCardQuestion = c.getResources().getDrawable(R.drawable.btn_swap_cards_background_battery);
            btn_text_color = c.getResources().getColor(R.color.btn_background_text_color_battery);
            hint_text_color = c.getResources().getColor(R.color.hint_text_color_day);
            background = c.getResources().getColor(R.color.background_color_battery);
            btnBackground = c.getResources().getDrawable(R.drawable.custom_button_battery);
            btnBackgroundStroke = c.getResources().getColor(R.color.btn_background_stroke_battery);
            deletBackground = c.getResources().getDrawable(R.drawable.btn_delete_card_background_battery);
            saveBackround = c.getResources().getDrawable(R.drawable.btn_save_card_background_battery);
            copyBackground = c.getResources().getDrawable(R.drawable.btn_copy_card_background_battery);
            closeBackground = c.getResources().getDrawable(R.drawable.btn_close_dialog_background_battery);
            btn_edit_card_background = c.getResources().getDrawable(R.drawable.btn_edit_card_background_battery);
            btn_add_card_background = c.getResources().getDrawable(R.drawable.btn_add_card_background_battery);

        }
        if (mode == 2) {
            btn_text_color = c.getResources().getColor(R.color.btn_text_color_night);
            hint_text_color = c.getResources().getColor(R.color.hint_text_color_night);
            background = c.getResources().getColor(R.color.background_color_night);
            btnBackground = c.getResources().getDrawable(R.drawable.custom_button_night);
            btnBackgroundStroke = c.getResources().getColor(R.color.btn_background_stroke_night);
            saveBackround = c.getResources().getDrawable(R.drawable.btn_save_card_background_night);
            copyBackground = c.getResources().getDrawable(R.drawable.btn_copy_card_background_night);
            deletBackground = c.getResources().getDrawable(R.drawable.btn_delete_card_background_night);
            swapCardQuestion = c.getResources().getDrawable(R.drawable.btn_swap_cards_background_night);
            closeBackground = c.getResources().getDrawable(R.drawable.btn_close_dialog_background_night);
            btn_edit_card_background = c.getResources().getDrawable(R.drawable.btn_edit_card_background_night);
            btn_add_card_background = c.getResources().getDrawable(R.drawable.btn_add_card_background_night);
        }

    }
}
