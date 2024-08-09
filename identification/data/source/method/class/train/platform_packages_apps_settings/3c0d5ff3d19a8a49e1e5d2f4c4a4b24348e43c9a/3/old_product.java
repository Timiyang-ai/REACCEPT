public void forgetNetwork() {
        if (mWifiConfig.ephemeral) {
            mWifiManager.disableEphemeralNetwork(mWifiConfig.SSID);
        } else if (mWifiConfig.isPasspoint()) {
            mWifiManager.removePasspointConfiguration(mWifiConfig.FQDN);
        } else {
            mWifiManager.forget(mWifiConfig.networkId, null /* action listener */);
        }
    }