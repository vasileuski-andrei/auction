package com.company.util;

import lombok.Data;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

@Data
public class LotCountdown implements Runnable {

    private Thread thread;
    private Integer saleTimeInSeconds;

    public LotCountdown(Integer saleTimeInSeconds) {
        this.saleTimeInSeconds = saleTimeInSeconds;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                saleTimeInSeconds--;

                if (saleTimeInSeconds == 0) {
                    timer.cancel();

                }
            }
        }, 0, 1000);


    }

    public String getSaleRemainingTime() {
        return LocalTime.MIN.plusSeconds(saleTimeInSeconds).toString();
    }



}
