@Test
    public void testHexStringToString() {
        assertEquals("ABC", ParseUtil.hexStringToString("414243"));
        assertEquals("ab00cd", ParseUtil.hexStringToString("ab00cd"));
        assertEquals("not hex", ParseUtil.hexStringToString("not hex"));
    }