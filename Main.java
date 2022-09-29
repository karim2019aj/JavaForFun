package com.App.IoTCoap;


import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Main {


    private static void printProgress(long startTime, long total, long current) {


        long eta = current == 0 ? 0 :
                (total - current) * (System.currentTimeMillis() - startTime) / current;

        String etaHms = current == 0 ? "N/A" :
                String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(eta),
                        TimeUnit.MILLISECONDS.toMinutes(eta) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(eta) % TimeUnit.MINUTES.toSeconds(1));

        StringBuilder string = new StringBuilder(140);

        int percent = (int) (current * 100 / total);
        string
                .append('\r')
                .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
                .append(String.format(" %d%% [", percent))
                .append(String.join("", Collections.nCopies(percent, "=")))
                .append('>')
                .append(String.join("", Collections.nCopies(100 - percent, " ")))
                .append(']')
                .append(String.join("", Collections.nCopies((int) (Math.log10(total)) - (int) (Math.log10(current)), " ")))
                .append(String.format(" %d/%d, ETA: %s", current, total, etaHms));


        System.out.print(string);
    }

    public static void main(String[] args) {

        long total = 140;
        long startTime = System.currentTimeMillis();

        StringBuilder Message = new StringBuilder(140);
        Message.append("Downloading Internet...")
                .append('\r');
        System.out.println(Message);
        for (int i = 1; i <= total; i = i + 3) {
            try {
                Thread.sleep(50);

                printProgress(startTime, total, i);
            } catch (InterruptedException e) {
            }
        }
        StringBuilder Nstring = new StringBuilder(140);
        Nstring
                .append("\n")
                .append("Finished");
        System.out.println(Nstring);
    }


}
