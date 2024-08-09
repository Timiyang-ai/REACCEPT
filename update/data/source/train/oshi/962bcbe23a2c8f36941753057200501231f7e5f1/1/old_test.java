@Test
    public void testHexStringToString() {
        assertEquals("ABC", ParseUtil.hexStringToString("414243"));
        assertEquals("not hex", ParseUtil.hexStringToString("not hex"));
    }