public void forgetNetwork() {
        if (mWifiInfo != null && mWifiInfo.isEphemeral()) {
            mWifiManager.disableEphemeralNetwork(mWifiInfo.getSSID());
        } else if (mWifiConfig != null) {
            if (mWifiConfig.isPasspoint()) {
                mWifiManager.removePasspointConfiguration(mWifiConfig.FQDN);
            } else {
                mWifiManager.forget(mWifiConfig.networkId, null /* action listener */);
            }
        }
    }