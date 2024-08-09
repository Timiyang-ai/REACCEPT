public byte[] trits() {
        TritSafe safe = tritSafe;
        if (safe == null) {
            synchronized (lock) {
                if (tritSafe == null) {
                    Objects.requireNonNull(byteSafe, "I need my bytes to be initialized in order to construct trits.");
                    byte[] src = bytes();
                    byte[] dest = new byte[Curl.HASH_LENGTH];
                    Converter.getTrits(src, dest);
                    tritSafe = new TritSafe(dest);
                }
                safe = tritSafe;
            }
        }
        return safe.trits;
    }