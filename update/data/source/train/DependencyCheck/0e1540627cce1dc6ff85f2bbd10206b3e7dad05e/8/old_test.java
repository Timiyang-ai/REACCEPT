@Test
    public void testGetCweName() {
        String cweId = "CWE-16";
        String expResult = "Configuration";
        String result = CweDB.getCweName(cweId);
        assertEquals(expResult, result);
    }