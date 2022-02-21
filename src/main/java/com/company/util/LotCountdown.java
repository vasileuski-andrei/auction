package com.company.util;

import com.company.service.LotService;
import lombok.Data;

import java.time.LocalTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.company.entity.LotStatus.SOLD;

@Data
public class LotCountdown implements Runnable {
    private Integer lotId;
    private Thread thread;
    private Integer saleTimeInSeconds;
    private static final LotService lotService = LotService.getInstance();

    public LotCountdown(Integer lotId, Integer saleTimeInSeconds) {
        this.lotId = lotId;
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
                    lotService.removeLotCountdown(lotId);
                    lotService.updateLot(lotId, SOLD);
                }
            }
        }, 0, 1000);

    }

    public String getSaleRemainingTime() {
        if (saleTimeInSeconds == null) {
            return "-";
        }
        return LocalTime.MIN.plusSeconds(saleTimeInSeconds).toString();
    }

}
