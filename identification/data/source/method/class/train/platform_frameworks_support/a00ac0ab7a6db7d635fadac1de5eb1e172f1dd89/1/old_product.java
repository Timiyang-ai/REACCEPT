public void skipToEnd() {
        if (!canSkipToEnd()) {
            throw new UnsupportedOperationException("Spring animations can only come to an end"
                    + " when there is damping");
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        if (mRunning) {
            if (mPendingPosition != UNSET) {
                mSpring.setFinalPosition(mPendingPosition);
                mPendingPosition = UNSET;
            }
            mValue = mSpring.getFinalPosition();
            mVelocity = 0;
            cancel();
        }
    }