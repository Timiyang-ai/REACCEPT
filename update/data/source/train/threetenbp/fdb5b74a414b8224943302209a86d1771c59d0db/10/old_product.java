public static DateTimeAdjuster nextOrCurrent(DayOfWeek dow) {
        if (dow == null) {
            throw new NullPointerException("DayOfWeek must not be null");
        }
        return new RelativeDayOfWeek(0, dow);
    }