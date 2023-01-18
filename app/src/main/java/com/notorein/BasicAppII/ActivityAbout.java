package com.notorein.BasicAppII;

import static com.notorein.BasicAppII.Parameter.Parameter.isTrialVersion;
import static com.notorein.BasicAppII.Parameter.Parameter.settingsWereUsed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.notorein.BasicApp.R;
import com.notorein.BasicAppII.Files.FilesReadSettings;
import com.notorein.BasicAppII.Files.FilesWriteSettings;
import com.notorein.BasicAppII.Strings.StringsUI;

public class ActivityAbout extends AppCompatActivity implements View.OnClickListener {

    private Context c;
    private Activity a;
    private int tapCounter = 1;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_about);
        c = this;
        a = this;
        FilesReadSettings.scanLessonSettingsFile(this);
        FilesReadSettings.scanStartUpSettingsFile(this,this);
        View layout = this.findViewById(R.id.layoutAbout);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        Trial.countCardsLeft(Trial.trialCount);
        StringsUI.refreshAboutText();
        TextView about = findViewById(R.id.textViewAbout);
        layout.setBackgroundColor(Colors.background);
        about.setBackgroundColor(Colors.background);
        about.setTextColor(Colors.textAbout);
        about.setText(StringsUI.aboutText);
        about.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        settingsWereUsed = true;
        FilesWriteSettings.writeStartupSettings(this);
        finish();
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
//        setCountdown();
        tapCounter++;
    }

    public void setCountdown() {
        int durationInMilliSeconds = 2000;
        /*Tick duration*/
        CountDownTimer countDown = new CountDownTimer(durationInMilliSeconds, 1000 /*Tick duration*/) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                if (tapCounter == 7) {
                    Toast toast = new Toast(c);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    Toast.makeText(c, "Pro Version: " + isTrialVersion, Toast.LENGTH_SHORT).show();
                    isTrialVersion = !isTrialVersion;
                    FilesWriteSettings.writeStartupSettings(a);
                    finish();
                }
                if (tapCounter == 3) {


                }
                tapCounter = 1;
            }
        };
        countDown.start();
    }
}
