@SuppressWarnings("ThrowableInstanceNeverThrown")
    void suspend() {
        Logger.global.log(Level.SEVERE, UNSUPPORTED_THREAD_METHOD,
                new UnsupportedOperationException());
    }