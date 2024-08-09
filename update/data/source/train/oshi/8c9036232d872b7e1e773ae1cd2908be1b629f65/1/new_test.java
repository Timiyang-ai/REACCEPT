@Test
    public void testGetProcessor() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        assertTrue(hal.getProcessor().getLogicalProcessorCount() > 0);
    }