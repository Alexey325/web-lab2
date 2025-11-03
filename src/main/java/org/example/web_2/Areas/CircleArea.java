package org.example.web_2.Areas;

import java.math.BigDecimal;

public class CircleArea {
    private final BigDecimal radius;

    public CircleArea(BigDecimal radius) {
        this.radius = radius;
    }

    public boolean contains(BigDecimal x, BigDecimal y) {
        return x.compareTo(BigDecimal.ZERO) <= 0 && y.compareTo(BigDecimal.ZERO) <= 0 && x.pow(2).add(y.pow(2)).compareTo(radius.pow(2)) <= 0;
    }
}
