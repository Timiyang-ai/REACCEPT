private boolean canForgetNetwork() {
        // TODO(65396674): create test for the locked down scenario
        return (mWifiInfo != null && mWifiInfo.isEphemeral())
                || (mWifiConfig != null && !isEditabilityLockedDown(mContext, mWifiConfig));
    }