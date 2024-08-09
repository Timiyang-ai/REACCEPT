public ChronoDate plus(SingleUnitPeriod period) {
        return plus(period.getAmount(), period.getUnit());
    }