public boolean isDeviceConnected(CachedBluetoothDevice cachedDevice) {
        if (cachedDevice == null) {
            return false;
        }
        final BluetoothDevice device = cachedDevice.getDevice();
        if (DBG) {
            Log.d(TAG, "isDeviceConnected() device name : " + cachedDevice.getName() +
                    ", is connected : " + device.isConnected() + " , is profile connected : "
                    + cachedDevice.isConnected());
        }
        return device.getBondState() == BluetoothDevice.BOND_BONDED && cachedDevice.isConnected();
    }