@SuppressWarnings("ThrowableInstanceNeverThrown")
    void resume() {
        Logger.global.log(Level.SEVERE, UNSUPPORTED_THREAD_METHOD,
                new UnsupportedOperationException());
    }