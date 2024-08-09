    @Test
    public void getDeviceForCurrentThread() throws Exception {
        CudaAffinityManager manager = new CudaAffinityManager();

        Integer deviceId = manager.getDeviceForCurrentThread();

        assertEquals(0, deviceId.intValue());

        manager.attachThreadToDevice(Thread.currentThread().getId(), 1);

        assertEquals(1, manager.getDeviceForCurrentThread().intValue());

        manager.attachThreadToDevice(Thread.currentThread().getId(), 0);

        assertEquals(0, manager.getDeviceForCurrentThread().intValue());
    }