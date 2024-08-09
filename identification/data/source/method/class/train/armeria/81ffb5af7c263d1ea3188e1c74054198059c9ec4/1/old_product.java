public static long currentTimeMicros() {
        if (javaVersion() == 8) {
            return TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());
        } else {
            // Java 9+ support higher precision wall time.
            final Instant now = Clock.systemUTC().instant();
            return TimeUnit.SECONDS.toMicros(now.getEpochSecond()) + TimeUnit.NANOSECONDS.toMicros(
                    now.getNano());
        }
    }