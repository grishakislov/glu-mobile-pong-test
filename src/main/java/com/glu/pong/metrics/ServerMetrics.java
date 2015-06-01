package com.glu.pong.metrics;

import com.glu.pong.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ServerMetrics {

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeService;

    @Autowired
    private SystemService systemService;

    @Bean
    public PublicMetrics databaseSize() {
        return () -> {
            Metric<Long> dbMetric = new Metric<>("pong.database.size_kb", systemService.dbSize() / 1024);
            return Arrays.asList(dbMetric);
        };
    }


}
