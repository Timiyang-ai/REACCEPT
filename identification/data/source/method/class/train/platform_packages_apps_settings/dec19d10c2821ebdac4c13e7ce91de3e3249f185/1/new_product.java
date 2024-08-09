private boolean canForgetNetwork() {
        return (mWifiInfo != null && mWifiInfo.isEphemeral()) || canModifyNetwork()
                || mAccessPoint.isPasspoint() || mAccessPoint.isPasspointConfig();
    }