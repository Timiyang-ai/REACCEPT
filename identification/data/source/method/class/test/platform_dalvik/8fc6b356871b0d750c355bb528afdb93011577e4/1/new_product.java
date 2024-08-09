@SuppressWarnings("ThrowableInstanceNeverThrown")
    void stop(Throwable throwable) {
        Logger.global.log(Level.SEVERE, UNSUPPORTED_THREAD_METHOD,
                new UnsupportedOperationException());
    }