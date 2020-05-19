package com.socblog.models.enums;

public enum ELevelUpOptions {
    LEVEL_UP_BY_POST(1.75), LEVEL_UP_BY_TAG(0.5), LEVEL_UP_BY_READ_POSTS(0.25);

    private double coefficient;

    ELevelUpOptions(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
