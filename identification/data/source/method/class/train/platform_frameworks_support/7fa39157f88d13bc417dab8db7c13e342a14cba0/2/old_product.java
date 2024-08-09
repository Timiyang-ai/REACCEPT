public void clear() {
        if (mSize == 0) {
            return;
        }
        final int prevSize = mSize;
        Arrays.fill(mData, 0, prevSize, null);
        mSize = 0;
        mCallback.onRemoved(0, prevSize);
    }