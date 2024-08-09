@Test
    public void testGetMemory() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        GlobalMemory globalMemory = hal.getMemory();
        assertNotNull(globalMemory);
        assertTrue(globalMemory.getTotal() > 0);
        assertTrue(globalMemory.getAvailable() >= 0);
        assertTrue(globalMemory.getAvailable() <= globalMemory.getTotal());
    }