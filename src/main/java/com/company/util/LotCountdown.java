package com.company.util;

import lombok.Data;

import java.util.Timer;
import java.util.TimerTask;

@Data
public class LotCountdown implements Runnable {

    private Thread thread;
    private Integer time = 1000;

    public LotCountdown() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                time--;

                if (time == 0) {
                    timer.cancel();

                }
            }
        }, 0, 1000);


    }



}
