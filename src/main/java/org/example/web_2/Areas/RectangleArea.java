package org.example.web_2.Areas;

import java.math.BigDecimal;

public class RectangleArea {
    private final BigDecimal width;
    private final BigDecimal height;

    public RectangleArea(BigDecimal width, BigDecimal height) {
        this.width = width;
        this.height = height;
    }

    public boolean contains(BigDecimal x, BigDecimal y) {
        return x.compareTo(BigDecimal.ZERO) >= 0 &&
                x.compareTo(width) <= 0 &&
                y.compareTo(BigDecimal.ZERO) >= 0 &&
                y.compareTo(height) <= 0;
    }


}
