public ChronoDate minus(SingleUnitPeriod period) {
        return minus(period.getAmount(), period.getUnit());
    }