package com.notorein.BasicAppII;


import static android.content.ContentValues.TAG;
import static com.notorein.BasicAppII.ActivityMain.context;
import static com.notorein.BasicAppII.Arrays.editedCardIndex;
import static com.notorein.BasicAppII.Arrays.lessonIndex;
import static com.notorein.BasicAppII.Arrays.lessonNames;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsArray;
import static com.notorein.BasicAppII.Arrays.loadedCardsAsList;
import static com.notorein.BasicAppII.Arrays.sortIndexCard;
import static com.notorein.BasicAppII.Arrays.sortLesson;
import static com.notorein.BasicAppII.Arrays.sortLessonCopy;
import static com.notorein.BasicAppII.Parameter.Parameter.mode;
import static com.notorein.BasicAppII.Parameter.Parameter.removeCard;
import static com.notorein.BasicAppII.UISettingsItems.createDrawable;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Dialogs.ViewMenuAd;
import com.notorein.BasicAppII.Dialogs.ViewOptions;
import com.notorein.BasicAppII.Files.FilesWriteLessons;
import com.notorein.BasicAppII.POI.POIStoreExcelFile;
import com.notorein.BasicAppII.Strings.StringsUI;

import java.io.IOException;
import java.util.LinkedList;

public class ActivityCards extends AppCompatActivity {

    private ListView list_view;
    private ImageView btn_edit;
    private CustomCardChooserAdapter adapter;
    private ConstraintLayout listViewCardsBackground;
    private String[][] tempArray;
    private int previousFirstVisibleItem = 0;
    private ConstraintLayout layout;
    private ImageView btn_copy;
    private ImageView btn_store;
    private ImageView btn_delete;
    private ImageView btn_close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cards_list_view);
        layout = findViewById(R.id.listViewCardsBackground);
        btn_edit = layout.findViewById(R.id.btn_edit);
        btn_copy = layout.findViewById(R.id.btn_copy);
        btn_delete = layout.findViewById(R.id.btn_delete);
        btn_store = layout.findViewById(R.id.btn_store_card);
        btn_close = layout.findViewById(R.id.btn_close);

        list_view = layout.findViewById(R.id.list_view);
//        listViewCardsBackground = findViewById(R.id.listViewCardsBackground);
//        if (removeCard) {
////            removeCardLogic();
//        }
        removeCard = false;
        tempArray = Arrays.sortLesson(loadedCardsAsArray);
        adapter = new CustomCardChooserAdapter(tempArray, this);
        list_view.setAdapter(adapter);
        setColor();
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

        btn_delete.setOnClickListener(c -> {
            ViewOptions options = new ViewOptions(this, mode, StringsUI.dialogDeleteCard, StringsUI.toastTextDeleteCard, () -> {
//                removeCard = true;
                removeCardLogicFromCardsActivity();
                this.recreate();
            });
            options.show();
        });

        btn_edit.setOnClickListener(c -> {
            ViewMenuAd menuInput = new ViewMenuAd(this, this, mode, listViewCardsBackground, tempArray, false, true);
            menuInput.getWindow().setBackgroundDrawable(createDrawable(Color.GRAY, Colors.background, 0, 0));
            menuInput.show();
            menuInput.setCancelable(false);
            menuInput.setCanceledOnTouchOutside(false);
        });

        btn_close.setOnClickListener(c -> {
            finish();
        });

        btn_copy.setOnClickListener(c -> {
            String[][] temp = Arrays.copyCard(loadedCardsAsArray, editedCardIndex);
            loadedCardsAsArray = temp;
            loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
            temp = sortLesson(temp);
            temp = sortLessonCopy(temp, editedCardIndex);
            FilesWriteLessons.writeFileWords(this, temp);
            editedCardIndex++;

            Toast.makeText(this, StringsUI.copiedCard, Toast.LENGTH_SHORT).show();
            this.recreate();
        });
    }


    public static void removeCardLogicFromCardsActivity() {
        String[][] tempArrayForDelete = loadedCardsAsArray;
//        int remove = Integer.parseInt(tempArrayForDelete[editedCardIndex][sortIndexCard]);
//        tempArrayForDelete = Arrays.sortLessonDelete(tempArrayForDelete, editedCardIndex);
        LinkedList<LinkedList<String>> tempList = Arrays.readArrayToList(loadedCardsAsArray);
        tempList.remove(editedCardIndex);
        tempArrayForDelete = Arrays.readListToArray(tempList);
        LinkedList loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
        loadedCardsAsArray = Arrays.sortLessonDelete(loadedCardsAsArray, editedCardIndex);
        loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
        loadedCardsAsList.remove(editedCardIndex);
        loadedCardsAsArray = Arrays.readListToArray(loadedCardsAsList);
        Arrays.cardlistLenght = loadedCardsAsArray.length;
        if (editedCardIndex > Arrays.cardlistLenght - 1) {
            editedCardIndex = Arrays.cardlistLenght - 1;
        }
        FilesWriteLessons.writeFileWords(context, tempArrayForDelete);


        try {
            POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArrayForDelete, lessonNames[lessonIndex]);
            poiStoreExcelFile.writeToExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeCardLogicFromEditMenu() {
        String[][] tempArrayForDelete = loadedCardsAsArray;
        int remove = Integer.parseInt(tempArrayForDelete[editedCardIndex][sortIndexCard]);
        tempArrayForDelete = Arrays.sortLessonDelete(tempArrayForDelete, remove);
        LinkedList<LinkedList<String>> tempList = Arrays.readArrayToList(loadedCardsAsArray);
        tempList.remove(editedCardIndex);
        tempArrayForDelete = Arrays.readListToArray(tempList);
        LinkedList loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
        loadedCardsAsArray = Arrays.sortLessonDelete(loadedCardsAsArray, remove);
        loadedCardsAsList = Arrays.readArrayToList(loadedCardsAsArray);
        loadedCardsAsList.remove(remove);
        loadedCardsAsArray = Arrays.readListToArray(loadedCardsAsList);
        Arrays.cardlistLenght = loadedCardsAsArray.length;
        if (editedCardIndex > Arrays.cardlistLenght - 1) {
            editedCardIndex = Arrays.cardlistLenght - 1;
        }
        FilesWriteLessons.writeFileWords(context, tempArrayForDelete);


        try {
            POIStoreExcelFile poiStoreExcelFile = new POIStoreExcelFile(tempArrayForDelete, lessonNames[lessonIndex]);
            poiStoreExcelFile.writeToExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setColor() {
        layout.setBackgroundColor(Colors.background);
//        list_view.setBackground(UISettingsItems.createDrawable(Colors.btn_text_color, Colors.btn_text_color, 3, 1));

//        if (mode == 0) {
        btn_close.setColorFilter(Colors.btn_text_color);
        btn_store.setColorFilter(Colors.btn_text_color);
        btn_delete.setColorFilter(Colors.btn_text_color);
        btn_copy.setColorFilter(Colors.btn_text_color);
        btn_edit.setColorFilter(Colors.btn_text_color);
//        } else {
//            btn_close.setColorFilter(Colors.background);
//            btn_store.setColorFilter(Colors.background);
//            btn_delete.setColorFilter(Colors.background);
//            btn_copy.setColorFilter(Colors.background);
//            btn_edit.setColorFilter(Colors.background);
//        }

    }


}

