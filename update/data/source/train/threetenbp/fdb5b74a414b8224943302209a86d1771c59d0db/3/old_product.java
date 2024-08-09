public static DateTimeAdjuster previousOrCurrent(DayOfWeek dow) {
        if (dow == null) {
            throw new NullPointerException("DayOfWeek must not be null");
        }
        return new RelativeDayOfWeek(1, dow);
    }