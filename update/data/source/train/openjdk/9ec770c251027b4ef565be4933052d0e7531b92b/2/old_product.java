public long toNanos() {
        long totalNanos = Math.multiplyExact(seconds, NANOS_PER_SECOND);
        totalNanos = Math.addExact(totalNanos, nanos);
        return totalNanos;
    }