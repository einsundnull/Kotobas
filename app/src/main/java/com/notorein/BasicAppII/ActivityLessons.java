package com.notorein.BasicAppII;

import static android.content.ContentValues.TAG;
import static com.notorein.BasicAppII.Arrays.cardlistLenght;
import static com.notorein.BasicAppII.Arrays.lessonFilesInCustomFolder;
import static com.notorein.BasicAppII.Arrays.lessonIndex;
import static com.notorein.BasicAppII.Arrays.lessonNames;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsArray;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsList;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsListFromExcelUpdate;
import static com.notorein.BasicAppII.Colors.btn_text_color;
import static com.notorein.BasicAppII.Parameter.Parameter.mode;
import static com.notorein.BasicAppII.Parameter.Parameter.settingsWereUsed;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.cellIndex;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.columnIndex;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.destinationDirectory;
import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.sheetIndex;
import static com.notorein.BasicAppII.UISettingsItems.createDrawable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Dialogs.Dialogs;
import com.notorein.BasicAppII.Dialogs.ViewOptions;
import com.notorein.BasicAppII.Files.FilesReadSettings;
import com.notorein.BasicAppII.Files.FilesWriteLessons;
import com.notorein.BasicAppII.Files.FilesWriteSettings;
import com.notorein.BasicAppII.POI.POIReadEXCELFile;
import com.notorein.BasicAppII.Parameter.Parameter;
import com.notorein.BasicAppII.Strings.StringsUI;

import java.io.File;
import java.util.ArrayList;

public class ActivityLessons extends AppCompatActivity {

    public Activity activity;
    public ListView list_view;
    private int checked;
    private ImageView btn_add;
    //    private EditText newLessonInput;
    private CustomLessonChooserAdapter adapter;
    //    private Button btn_update_excel;
    private TextView btn_update_from_excel;
    private ConstraintLayout layout;
    private ImageView btn_close;
    //    private ImageView btn_store;
    private ImageView btn_copy;
    private ImageView btn_delete;
    private Context context;
    private Dialogs dialogs;
    private int scrollDirection = 0;
    private int previousFirstVisibleItem;
    private int firstVisibleItemTemp;
    private int temp;
    private boolean wasScrolled;
//    private ImageView imageViewCloseDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lesson_list_view);
        activity = this;
        context = this;
        FilesReadSettings.scanLessonSettingsFile(this);
        adapter = new CustomLessonChooserAdapter(ActivityLessons.this, ActivityLessons.this, lessonNames);
        setList_view(adapter);
        setColor();
    }


    private void setList_view(CustomLessonChooserAdapter adapter) {
        layout = findViewById(R.id.listviewActivityLayout);
        list_view = layout.findViewById(R.id.list_view);
        btn_add = layout.findViewById(R.id.btn_add_lesson);
//        btn_edit = layout.findViewById(R.id.btn_edit);
        btn_copy = layout.findViewById(R.id.btn_copy);
        btn_delete = layout.findViewById(R.id.btn_delete);
//        btn_store = layout.findViewById(R.id.btn_store_card);
        btn_close = layout.findViewById(R.id.btn_close);
        btn_update_from_excel = findViewById(R.id.btn_update_from_excel);
        btn_update_from_excel.setText(StringsUI.updateFromExcelFileText);
        list_view.setAdapter(adapter);
        list_view.setItemChecked(lessonIndex, true);
        checked = list_view.getCheckedItemPosition();
//// Get the middle position of the ListView
//        int middle = list_view.getHeight() / 2;
//
//// Get the position of the item with the stored index
//        int index = lessonIndex;
//
//// Get the view of the item with the stored index
//        View view = (TextView)list_view.getSelectedItem();
//
//// Calculate the position of the item relative to the middle of the ListView
//        int positionToScrollTo = index + (view.getTop() - middle);
//
//// Scroll to the calculated position
//        list_view.smoothScrollToPosition(positionToScrollTo);


        list_view.setOnItemClickListener((parent, view1, position, id) -> {
            LinearLayout ln = null;
            TextView txtView = null;

            // this line removes the checked status from the previous item in the listView and gives it back its old color
            for (int i = 0; i < lessonNames.length; i++) {
                try {
                    ln = (LinearLayout) list_view.getChildAt(i);
                    txtView = (TextView) ln.getChildAt(0);
                    if (mode != 0) {
                        txtView.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.background, 0, 0));
                        txtView.setTextColor(btn_text_color);
                    } else {
                        txtView.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.background, 0, 0));
                        txtView.setTextColor(btn_text_color);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            previousFirstVisibleItem = previousFirstVisibleItem < 0 ? previousFirstVisibleItem = 0 : previousFirstVisibleItem;
            previousFirstVisibleItem = previousFirstVisibleItem > lessonNames.length - 1 ? previousFirstVisibleItem = lessonNames.length - 1 : previousFirstVisibleItem;
            int temp = position;
            temp = temp - (previousFirstVisibleItem);
            ln = (LinearLayout) parent.getChildAt(temp);
            txtView = (TextView) ln.getChildAt(0);
            if (mode != 0) {
                txtView.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.background, 0, 4));
                txtView.setTextColor(btn_text_color);
            } else {
                txtView.setBackground(createDrawable(Colors.btn_background_stroke_day_selected, Colors.background, 0, 4));
                txtView.setTextColor(btn_text_color);
            }
            lessonIndex = position;
            FilesWriteSettings.writeLessonSettings(this);
        });

        list_view.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                previousFirstVisibleItem = firstVisibleItem;
                Log.i(TAG, previousFirstVisibleItem + "  setListView: " + lessonIndex + " " + lessonNames.length);
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
        });


        btn_add.setOnClickListener(c -> {
            Parameter.recreateActivity = true;
            Dialogs dialogs = new Dialogs(this, this);
            dialogs.showStoreNewLessonDialog();
        });

        btn_delete.setOnClickListener(c -> {
            Parameter.recreateActivity = true;

            ViewOptions options = new ViewOptions(this, mode, StringsUI.dialogDeleteLesson, StringsUI.toastTextDeleteLesson, () -> {

                Parameter.deletedLesson = true;
                lessonIndex--;
                if (lessonIndex < 0) {
                    lessonIndex = 0;
                }
                FilesWriteSettings.writeStartupSettings(activity);
                FilesWriteSettings.writeLessonSettings(activity);

                File file = lessonFilesInCustomFolder[lessonIndex];
                file.delete();

                File fileTxt = new File(this.getFilesDir(), Arrays.lessonNames[Arrays.lessonIndex].replace(".xlsx", ""));
                if (fileTxt.exists()) {
                    fileTxt.delete();
                }

                ArrayList<String> temp = Arrays.readArrayToList(lessonNames);
                temp.remove(lessonIndex);
                lessonNames = (String[]) temp.toArray();

                ArrayList<File> fileTemp = Arrays.readArrayToList(lessonFilesInCustomFolder);
                fileTemp.remove(lessonIndex);
                lessonFilesInCustomFolder = (File[]) fileTemp.toArray();
            });
            options.show();
        });


        btn_update_from_excel.setOnClickListener(c -> {
            ViewOptions options = new ViewOptions(activity, mode, StringsUI.updateFromExcelFileText, StringsUI.toastTextUpdatedLesson, () -> {
                POIReadEXCELFile poi = new POIReadEXCELFile(new File(destinationDirectory, lessonNames[lessonIndex]), sheetIndex, columnIndex, cellIndex);
                loadedCardsAsListFromExcelUpdate = poi.scanExcelByWhileLoopForWordListView();
                String[][] loadedCardsAsArrayFromExcelUpdate = poi.convertCardsArrayListToArray(loadedCardsAsListFromExcelUpdate);
                loadedCardsAsArrayFromExcelUpdate = Arrays.sortLesson(loadedCardsAsArrayFromExcelUpdate);
                loadedCardsAsListFromExcelUpdate = Arrays.readArrayToList(loadedCardsAsArrayFromExcelUpdate);
                // make this an array and sort it

                loadedCardsAsList = Arrays.compareLists(loadedCardsAsListFromExcelUpdate, loadedCardsAsList);
                loadedCardsAsArray = poi.convertCardsArrayListToArray(loadedCardsAsList);
                cardlistLenght = loadedCardsAsList.size();
                FilesWriteLessons.writeFileWords(this, loadedCardsAsArray);
            });
            options.show();
        });

        btn_close.setOnClickListener(c -> {
            settingsWereUsed = true;
            FilesWriteSettings.writeLessonSettings(this);
            FilesWriteSettings.writeStartupSettings(this);
            Intent intent = new Intent(this, ActivityMain.class);
            startActivity(intent);
            finish();
        });

//        btn_update_excel.setOnClickListener(c -> {
//            String[][] tempArrayForNewCard = loadedCardsAsArray;
//            String[][] tempArray = new String[loadedCardsAsArray.length][lessonsTemplateCardMetaInfoArrayMinLength];
//            for (int i = 0; i < loadedCardsAsArray.length; i++) {
//                tempArrayForNewCard[i] = POIReadEXCELFile.fillMissingCellValueLogic(0, lessonsTemplateCardMetaInfoArrayMinLength, tempArrayForNewCard[i]);
//                tempArray[i] = tempArrayForNewCard[i];
//            }
//            loadedCardsAsArray = tempArray;
//            FilesWriteLessons.writeFileWords(activity);
//            tempArrayForNewCard = Arrays.sortLesson(tempArray);
//            POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArrayForNewCard, lessonNames[lessonIndex].toString());
//            try {
//                poiStoreExcelFile.writeToExcel();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        settingsWereUsed = true;
        FilesWriteSettings.writeLessonSettings(this);
        FilesWriteSettings.writeStartupSettings(this);
        Intent intent = new Intent(this, ActivityMain.class);
        startActivity(intent);
    }

    private void setColor() {
        layout.setBackgroundColor(Colors.background);
        list_view.setBackgroundColor(Colors.background);
        btn_update_from_excel.setTextColor(Color.BLACK);
        btn_add.setBackground(UISettingsItems.createDrawable(Color.DKGRAY, Color.LTGRAY, 100, 3));
        btn_update_from_excel.setBackground(UISettingsItems.createDrawable(Color.DKGRAY, Color.LTGRAY, 5, 3));
        layout.setBackgroundColor(Colors.background);
        list_view.setBackground(Colors.btnBackground);

        btn_close.setColorFilter(btn_text_color);
        btn_delete.setColorFilter(btn_text_color);
        btn_copy.setColorFilter(btn_text_color);
    }
}