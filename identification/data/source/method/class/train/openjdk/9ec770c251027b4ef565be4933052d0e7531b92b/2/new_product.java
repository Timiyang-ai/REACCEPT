public long toNanos() {
        long tempSeconds = seconds;
        long tempNanos = nanos;
        if (tempSeconds < 0) {
            // change the seconds and nano value to
            // handle Long.MIN_VALUE case
            tempSeconds = tempSeconds + 1;
            tempNanos = tempNanos - NANOS_PER_SECOND;
        }
        long totalNanos = Math.multiplyExact(tempSeconds, NANOS_PER_SECOND);
        totalNanos = Math.addExact(totalNanos, tempNanos);
        return totalNanos;
    }