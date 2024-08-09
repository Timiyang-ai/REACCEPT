@Test
    public void testRemoveDevice() throws Exception {
        ctrl.removeDevice(deviceInfo1.getDeviceId());
        assertFalse("Incorrect device removal", ctrl.getDevicesMap().containsKey(deviceId1));
    }