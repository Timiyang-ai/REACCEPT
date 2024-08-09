public static TemporalAdjuster lastDayOfMonth() {
        return (temporal) -> temporal.with(DAY_OF_MONTH, temporal.range(DAY_OF_MONTH).getMaximum());
    }