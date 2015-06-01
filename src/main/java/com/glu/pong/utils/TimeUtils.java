package com.glu.pong.utils;

import java.time.Instant;

public class TimeUtils {

    public static long getUnixTime() {
        return Instant.now().getEpochSecond();
    }

}
