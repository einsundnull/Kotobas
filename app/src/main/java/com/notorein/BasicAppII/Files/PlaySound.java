package com.notorein.BasicAppII.Files;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.TextView;

import com.notorein.BasicAppII.interfaces.PlaySoundIF;

public class PlaySound implements PlaySoundIF {
    private Context context;
    private TextView view;
    private MediaPlayer mediaPlayer;

    public PlaySound(Object view, Context context) {
//        this.view = (TextView) view;
//        this.context = context;
    }

    @Override
    public void playSoundOnShow() {
        playSound();
    }


    public void playSound() {
//        String soundFileDirectory = Arrays.questioning[1][Arrays.fileInfoI];
////        String soundFileDirectory = Lessons.lessonNames[Lessons.lessonIndex];
//        String fileName = view.getText().toString().replaceAll(" ", "_").replace("/", "").replace("(", "").replace(")", "").replace("|", "");
//        if (!new File(soundFileDirectory).exists()) {
//            new File(soundFileDirectory).mkdir();
//        }
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    mediaPlayer = new MediaPlayer();
//                    AssetFileDescriptor afd = context.getAssets().openFd(soundFileDirectory + File.separator + fileName + UIStrings.wav);
//                    mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
//                    mediaPlayer.setOnCompletionListener(MediaPlayer::release);
//                    mediaPlayer.prepare();
//                    mediaPlayer.start();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread.setDaemon(true);
//        thread.start();
    }
}
