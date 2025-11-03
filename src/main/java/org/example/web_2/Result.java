package org.example.web_2;

import java.math.BigDecimal;

public class Result {
    private final BigDecimal x;
    private final BigDecimal y;
    private final BigDecimal r;
    private final String hit;
    private final String localTime;
    private final String processingTime;

    public Result(BigDecimal x, BigDecimal y, BigDecimal r, String hit, String localTime, String processingTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.localTime = localTime;
        this.processingTime = processingTime;

    }

    public BigDecimal getX() {
        return x;
    }

    public BigDecimal getY() {
        return y;
    }

    public BigDecimal getR() {
        return r;
    }

    public String getHit() {
        return hit;
    }

    public String getLocalTime() {
        return localTime;
    }

    public String getProcessingTime() {
        return processingTime;
    }

}
