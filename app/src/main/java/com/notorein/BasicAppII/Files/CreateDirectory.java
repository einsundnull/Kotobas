package com.notorein.BasicAppII.Files;

//import static com.notorein.BasicAppII.Parameter.ParameterEXCEL.destinationDirectory;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class CreateDirectory extends Activity {

    //    private String folderName;
    private static final int PERMISSION_REQUEST_CODE = 7;
    private String folderName;
    private Activity activity;
    private Context context;


    public CreateDirectory(Context context, Activity activity, String folderName) {
        this.activity = activity;
        this.folderName = folderName;
        this.context = context;
    }


    public void askPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if (requestCode == 100 && (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            createDirectory();

        } else {
            Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void createDirectory() {
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory() + "/" + folderName);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            createCustomDirectory(new File(Environment.getExternalStorageDirectory(), folderName.trim()));
//            createSettingsFiles();
        } else {
            askPermission();
        }
    }

    public void createCustomDirectory(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public String getFolderName() {
        return folderName;
    }


    public void createDirectory(File file) {
//        File file = new File(Environment.getExternalStorageDirectory(), folderName);
        if (!file.exists()) {
            file.mkdirs();
            if (file.isDirectory()) {
                Toast.makeText(activity, "Folder Created Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(activity, "Something Else Created Successfully", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity, "Folder Already Exists", Toast.LENGTH_SHORT).show();
        }
    }


}
