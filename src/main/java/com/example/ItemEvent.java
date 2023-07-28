package com.example;

import java.net.URL;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

public class ItemEvent implements EventHandler<ActionEvent> {

    private final Text tTime;
    private volatile boolean counting = false;

    private MediaPlayer mediaPlayer;
 
    private int count = 0;
    private final int H_COUNT = 3600;
    private final int M_COUNT = 60;
    private final int S_COUNT = 1;

    public ItemEvent(Text text) {
        this.tTime = text;
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getTarget();

        // timer の時間を設定
        String id = btn.getId();

        // counting時は、＋－ は押せない。
        if (!counting) {

            if (id.equals("hPlusBtn")) {

                count += H_COUNT;

            } else if (id.equals("hMinusBtn")) {

                // count が、3600未満なら何もしない
                if (count < H_COUNT)
                    return;

                count -= H_COUNT;

            } else if (id.equals("mPlusBtn")) {

                count += M_COUNT;

            } else if (id.equals("mMinusBtn")) {

                // count が、60未満なら何もしない
                if (count < M_COUNT)
                    return;

                count -= M_COUNT;

            } else if (id.equals("sPlusBtn")) {

                count += S_COUNT;

            } else if (id.equals("sMinusBtn")) {

                // count が、1未満なら何もしない
                if (count < S_COUNT)
                    return;

                count -= S_COUNT;
            }

            SetTime(count);
        }

        // timer start stop
        String text = btn.getText();
        String[] btnAction = { "Start", "Stop", "Clear" };

        if (text.equals(btnAction[0])) {
            // timer start
            counting = true;
            CountDownTimer();

        } else if (text.equals(btnAction[1])) {
            // timer stop
            counting = false;

            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();// 解放処理
                mediaPlayer = null; // mediaPlayerの参照をnullに設定
            }
        } else if (text.equals(btnAction[2])) {
            count = 0;
            SetTime(count);
        }
    }

    private void SetTime(int tLeft) {

        int hr = tLeft / 3600;
        int min = (tLeft % 3600) / 60;
        int sec = tLeft % 60;

        int[] times = { hr, min, sec };
        String[] tStr = new String[3];

        for (int i = 0; i < times.length; i++) {

            String str = Integer.valueOf(times[i]).toString();
            tStr[i] = times[i] < 10 ? "0" + str : str;
        }

        tTime.setText(tStr[0] + ":" + tStr[1] + ":" + tStr[2]);

    }

    private void playSound() {

        // 音声ファイルのパスを指定
        String soundFile = "/assets/sample.mp3";

        final URL resource = getClass().getResource(soundFile);
        Media mediafile = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(mediafile);
        mediaPlayer.play();



        // 再生が終了した後の処理を設定
        mediaPlayer.setOnEndOfMedia(() -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose(); // 解放処理

                // この設定により、音楽の再生終了後にstopが押されてもエラーにならない
                mediaPlayer = null;
            }
        });
    }

    private void CountDownTimer() {

        Thread countdownThread = new Thread(() -> {
            while (count > 0 && counting) {
                Platform.runLater(() -> {
                    SetTime(count);
                });

                count--;

                // カウントが0になったら音を再生
                if (count == 0) {
                    playSound();
                    counting = false;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> {
                // System.out.println("カウントダウン終了");
            });
        });

        countdownThread.start();
    }
}
