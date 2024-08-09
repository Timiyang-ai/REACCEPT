public long toNanos() {
        long millis = Math.multiplyExact(seconds, NANOS_PER_SECOND);
        millis = Math.addExact(millis, nanos);
        return millis;
    }