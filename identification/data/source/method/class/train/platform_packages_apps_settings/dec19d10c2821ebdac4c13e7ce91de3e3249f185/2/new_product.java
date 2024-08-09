private boolean canForgetNetwork() {
        return mWifiInfo != null && mWifiInfo.isEphemeral() || mWifiConfig != null;
    }