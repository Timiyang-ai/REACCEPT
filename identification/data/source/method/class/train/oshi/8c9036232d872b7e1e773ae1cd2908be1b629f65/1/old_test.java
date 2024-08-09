@Test
    public void testGetProcessorNumber() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        for (int i = 0; i < hal.getProcessors().length; i++) {
            assertEquals(i, hal.getProcessors()[i].getProcessorNumber());
        }
    }