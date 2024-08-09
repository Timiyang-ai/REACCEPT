@Test
    public void testGetMemory() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        Memory memory = hal.getMemory();
        assertNotNull(memory);
        assertTrue(memory.getTotal() > 0);
        assertTrue(memory.getAvailable() >= 0);
        assertTrue(memory.getAvailable() <= memory.getTotal());
    }