@Override
    public boolean isAvailable() {
        return mUm.isAdminUser() || Utils.isDemoUser(mContext);
    }