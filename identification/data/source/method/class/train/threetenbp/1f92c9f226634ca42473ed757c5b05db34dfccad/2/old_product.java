public ChronoDate minus(Period period) {
        return minus(period.getAmount(), period.getUnit());
    }