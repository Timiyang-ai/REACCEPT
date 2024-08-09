public boolean isDeviceConnected(CachedBluetoothDevice cachedDevice) {
        if (cachedDevice == null) {
            return false;
        }
        final BluetoothDevice device = cachedDevice.getDevice();
        return device.getBondState() == BluetoothDevice.BOND_BONDED && device.isConnected();
    }