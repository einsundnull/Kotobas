package com.notorein.BasicAppII.EXCEL;

import static android.content.ContentValues.TAG;
import static android.content.Context.DOWNLOAD_SERVICE;
import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.widget.Toast.LENGTH_LONG;

import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.CookieManager;
import android.widget.Toast;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;

public class DownloadFile {
    private String webSiteToCheckIfAvailable;    // This is the link that will be opened in order to download the file
    private File downloadFile;
    String getURL = "https://drive.google.com/uc?export=download&id=11tY1LXdSdh0MQKDjRdE2bcdMkZD32xVL";
    //    String getURL = " https://docs.google.com/spreadsheets/d/10dFq9qHydXANbDC0GnPMIWMP6eF7QV8hREzgxASDNVs/edit?usp=share_link";
    String folderName = "MyFolder";
    // Put the name of the sub directory you want to download into in this String. Replace "MyFolder"
    String destinationDirectory = Environment.getExternalStorageDirectory() + "/" + folderName + "/";
    // This is the name the file that is downloaded will be stored.
    private String fileName = "testFile";
    // This is the name the file format in which the downloaded file will be stored.
    private String fileExtension = ".txt";

    public String getGetURL() {
        return getURL;
    }

    public void setGetURL(String getURL) {
        this.getURL = getURL;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setNameOfDownloadTargetDirectory(String folderName) {
        this.folderName = folderName;
    }

    public String getDestinationDirectory() {
        return destinationDirectory;
    }

    public void setDestinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public File getDownloadFile() {
        return downloadFile;
    }

    public void setDownloadFile(File downloadFile) {
        this.downloadFile = downloadFile;
    }


    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    //This method actually checks if device is connected to internet.
    //There is the possibility it's connected to a network but not to internet.

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName(webSiteToCheckIfAvailable);
            //You can replace it with your name
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }


    public void downloadFile(Context context) {
        // Here the download starts
        boolean download = false;

//        download = isNetworkAvailable(context);
//        download = isInternetAvailable(getURL);
//        download = isInternetAvailable();
        download = true;
        if (download) {
            getURL = getURL.trim();
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(getURL));
//            String title = URLUtil.guessFileName(getURL, null, null);
            // I don't know what that is for.
//            request.setTitle("");
            request.setDescription("Downloading File");
            String cookie = CookieManager.getInstance().getCookie(getURL);
            request.addRequestHeader("cookie", cookie);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            // This line stores the file in the public download folder.
//            request.setDestinationInExternalPublicDir(DIRECTORY_DOWNLOADS, "fileName + fielExtension");
            // Here the file gets it's name and format.
            downloadFile = new File(destinationDirectory, fileName + fileExtension);
            request.setDestinationUri(Uri.fromFile(downloadFile));

            File storageDirectory = Environment.getExternalStorageDirectory();
            String folder = storageDirectory.getAbsolutePath() + destinationDirectory;
            Log.i(TAG, "storageDirectory: " + storageDirectory);
            Log.i(TAG, "folder: " + folder);

            DownloadManager downloadManager = null;
            downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
            Toast toast = new Toast(context);
            toast.makeText(context, "DOWNLOAD STARTED", LENGTH_LONG).show();
        } else {
            Toast toast = new Toast(context);
            toast.makeText(context, "Please Check Your Network Connection", LENGTH_LONG).show();
        }
    }

    public String[] scanFileListFromServer(Context context) {
        ArrayList<String> tempList = new ArrayList<String>();
//        try {
        File file = context.getExternalFilesDir(DIRECTORY_DOWNLOADS);
        System.out.println(file);
        String path = file.getPath() + File.separator + fileName;
        System.out.println(path);
//            Scanner scn = new Scanner(new File(path));
//            String str;
//            while (scn.hasNext()) {
//                str = scn.next();
//                tempList.add(str);
//                System.out.println(str);
//            }
//            scn.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String[] list = new String[tempList.size()];
//        list = (String[]) tempList.toArray();
//        System.out.println("#######################");
//        for (int i = 0; i < list.length; i++) {
//            System.out.println(list[i]);
//        }
//        System.out.println("#######################");
        return null;
    }
}
