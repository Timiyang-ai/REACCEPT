@MainThread
    public void setValue(T value) {
        mVersion++;
        mData = value;
        mObservers.forEach(mDispatchCallback);
    }