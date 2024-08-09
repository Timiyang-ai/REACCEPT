public QuarterOfYear minus(long quarters) {
        return plus(-(quarters % 4));
    }