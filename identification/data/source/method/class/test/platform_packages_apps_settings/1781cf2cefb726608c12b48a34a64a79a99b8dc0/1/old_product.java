@Override
    public boolean isAvailable() {
        if (mAppRow == null) {
            return false;
        }
        if (mAppRow.banned) {
            return false;
        }
        if (mChannelGroup != null) {
            return !mChannelGroup.isBlocked();
        }
        if (mChannel != null) {
            return mChannel.getImportance() != IMPORTANCE_NONE;
        }
        return true;
    }