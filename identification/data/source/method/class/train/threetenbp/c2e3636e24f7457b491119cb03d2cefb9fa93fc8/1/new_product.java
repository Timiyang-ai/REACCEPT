public long toNanos() {
        long millis = DateTimes.safeMultiply(seconds, 1000000000);
        millis = DateTimes.safeAdd(millis, nanos);
        return millis;
    }