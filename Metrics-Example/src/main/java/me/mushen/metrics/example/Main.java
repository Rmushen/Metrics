package me.mushen.metrics.example;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

/**
 * @Desc
 * @Author Remilia
 * @Create 2018-05-29
 */
public class Main {

    private static final MetricRegistry metrics = new MetricRegistry();


    public static void main(String[] args){
        startReport();
        Meter requests = metrics.meter("requests");
        requests.mark();
        wait5Seconds();
    }

    private static void startReport() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    private static void wait5Seconds() {
        try {
            Thread.sleep(5*1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
