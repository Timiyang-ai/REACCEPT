@Test
    public void testGetName() {
        String cweId = "CWE-16";
        String expResult = "Configuration";
        String result = CweDB.getName(cweId);
        assertEquals(expResult, result);

        cweId = "CWE-260000";
        result = CweDB.getName(cweId);
        assertNull(result);
    }