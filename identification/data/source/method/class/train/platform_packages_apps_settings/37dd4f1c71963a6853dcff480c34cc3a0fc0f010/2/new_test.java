    @Test
    public void isDeviceConnected_deviceConnected() {
        doReturn(BluetoothDevice.BOND_BONDED).when(mBluetoothDevice).getBondState();
        doReturn(true).when(mBluetoothDevice).isConnected();

        assertThat(mBluetoothDeviceUpdater.isDeviceConnected(mCachedBluetoothDevice)).isTrue();
    }