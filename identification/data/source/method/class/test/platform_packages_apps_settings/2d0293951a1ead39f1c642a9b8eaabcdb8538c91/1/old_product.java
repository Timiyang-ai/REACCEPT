@Override
    public boolean isAvailable() {
        return mUm.isAdminUser() || Utils.isCarrierDemoUser(mContext);
    }