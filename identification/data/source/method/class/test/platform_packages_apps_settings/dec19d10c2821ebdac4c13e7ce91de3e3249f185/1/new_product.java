private boolean canForgetNetwork() {
        return (mWifiInfo != null && mWifiInfo.isEphemeral()) || canModifyNetwork();
    }