public long toMillis() {
        long millis = DateTimes.safeMultiply(seconds, 1000);
        millis = DateTimes.safeAdd(millis, nanos / 1000000);
        return millis;
    }