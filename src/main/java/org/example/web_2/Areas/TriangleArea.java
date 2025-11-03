package org.example.web_2.Areas;

import java.math.BigDecimal;

public class TriangleArea {
    private final BigDecimal sideX;
    private final BigDecimal sideY;

    public TriangleArea(BigDecimal sideX,  BigDecimal sideY) {
        this.sideX = sideX;
        this.sideY = sideY;
    }

    public boolean contains(BigDecimal x, BigDecimal y) {
        if (x.compareTo(BigDecimal.ZERO) < 0 || y.compareTo(BigDecimal.ZERO) > 0 || x.compareTo(sideX) > 0 || y.compareTo(sideY) < 0) {
            return false;
        }

        BigDecimal lineY = x.multiply(BigDecimal.valueOf(2)).add(sideY);

        return y.compareTo(lineY) >= 0;
    }
}
