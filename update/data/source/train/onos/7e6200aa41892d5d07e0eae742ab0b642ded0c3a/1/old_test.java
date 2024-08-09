@Test
    public void testDisconnectDevice() throws Exception {
        ctrl.disconnectDevice(deviceInfo1);
        assertFalse("Incorrect device removal", ctrl.getDevicesMap().containsKey(deviceId1));
    }