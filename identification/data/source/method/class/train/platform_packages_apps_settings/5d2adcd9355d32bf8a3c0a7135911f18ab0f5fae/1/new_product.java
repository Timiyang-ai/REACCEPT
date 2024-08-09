private void setDeviceName(String deviceName) {
        mDeviceName = deviceName;
        setSettingsGlobalDeviceName(deviceName);
        setBluetoothDeviceName(deviceName);
        setTetherSsidName(deviceName);
        mPreference.setSummary(getSummary());
    }