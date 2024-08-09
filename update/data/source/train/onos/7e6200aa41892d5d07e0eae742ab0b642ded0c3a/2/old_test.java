@Test
    public void testRemoveDevice() throws Exception {
        ctrl.removeDevice(deviceInfo1);
        assertFalse("Incorrect device removal", ctrl.getDevicesMap().containsKey(deviceId1));
    }