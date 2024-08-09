public TAIInstant withNanoOfSecond(int nanoOfSecond) {
        if (nanoOfSecond < 0 || nanoOfSecond >= NANOS_PER_SECOND) {
            throw new IllegalArgumentException("NanoOfSecond must be from 0 to 999,999,999");
        }
        return ofTAISeconds(seconds, nanoOfSecond);
    }