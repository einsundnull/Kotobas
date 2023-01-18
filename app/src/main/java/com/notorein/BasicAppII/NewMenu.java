package com.notorein.BasicAppII;

import static com.notorein.BasicAppII.Strings.StringsUI.menuDialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.notorein.BasicApp.R;

import java.util.ArrayList;
import java.util.List;

public class NewMenu {
    // Create the Dialog
   public NewMenu(Context context){
       Dialog dialog = new Dialog(context);

// Set the layout for the Dialog
       dialog.setContentView(R.layout.new_menu);

       // Find the ListView in the layout
       ListView listView = (ListView) dialog.findViewById(R.id.list_view);

       // Create a String[] array for the items in the ListView
//       String[] listViewItems = {"Item 1", "Item 2", "Item 3"};
       String[] listViewItems = menuDialogs;

       // Create a List of String[] arrays for the items in the drop-down menus
       List<String[]> dropDownMenuItems = new ArrayList<>();
       dropDownMenuItems.add(Arrays.lessonNames);
       dropDownMenuItems.add(Arrays.loadedCardsAsArray[Arrays.lessonIndex]);
       dropDownMenuItems.add(new String[]

               {
                       "Menu Item 6"
               });

       // Create a ListView adapter
       ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listViewItems);

// Set the adapter for the ListView
       listView.setAdapter(adapter);

// Set the ListView's OnItemClickListener to show a drop-down menu when an item is clicked
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

       {
           @Override
           public void onItemClick (AdapterView < ? > parent, View view, int position, long id){
               // Get the drop-down menu items for the clicked item
               String[] menuItems = dropDownMenuItems.get(position);

               // Create a PopupMenu to show the drop-down menu
               PopupMenu popupMenu = new PopupMenu(context, view);

               // Inflate the menu with the drop-down menu items
               for (int i = 0; i < menuItems.length; i++) {
                   popupMenu.getMenu().add(Menu.NONE, i, i, menuItems[i]);
               }

               // Set an OnMenuItemClickListener to handle menu item clicks
               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       // Do something with the clicked menu item
                       return true;
                   }
               });

               // Show the drop-down menu
               popupMenu.show();
           }
       });

// Show the Dialog
       dialog.show();
   }

}