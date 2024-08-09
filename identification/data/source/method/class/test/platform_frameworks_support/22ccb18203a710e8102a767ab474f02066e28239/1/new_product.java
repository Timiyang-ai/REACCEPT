public @RepeatMode int getRepeatMode() {
        synchronized (mLock) {
            return mRepeatMode;
        }
    }