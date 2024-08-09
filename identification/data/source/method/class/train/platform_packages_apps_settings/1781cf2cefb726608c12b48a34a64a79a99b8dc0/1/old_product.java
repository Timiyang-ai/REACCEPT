@Override
    public boolean isAvailable() {
        if (mAppRow == null) {
            return false;
        }
        if (mAppRow.banned) {
            return false;
        }
        if (mChannel != null) {
            return mChannel.getImportance() != IMPORTANCE_NONE;
        }
        if (mChannelGroup != null && mChannelGroup.getGroup() == null) {
            return !mChannelGroup.isBlocked();
        }
        return true;
    }