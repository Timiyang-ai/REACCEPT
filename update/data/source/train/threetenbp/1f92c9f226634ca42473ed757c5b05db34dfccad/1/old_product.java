public ChronoDate plus(Period period) {
        return plus(period.getAmount(), period.getUnit());
    }