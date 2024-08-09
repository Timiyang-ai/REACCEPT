public void setValue(T value) {
        // we keep it in pending data so that last set data wins (e.g. we won't be in a case where
        // data is set on the main thread at a later time is overridden by data that was set on a
        // background thread.
        synchronized (mDataLock) {
            mPendingData = value;
        }
        AppToolkitTaskExecutor.getInstance().executeOnMainThread(new Runnable() {
            @Override
            public void run() {
                synchronized (mDataLock) {
                    if (mPendingData != NOT_SET) {
                        mVersion++;
                        mData = mPendingData;
                        mPendingData = NOT_SET;
                    }
                }
                dispatchingValue(null);
            }
        });
    }