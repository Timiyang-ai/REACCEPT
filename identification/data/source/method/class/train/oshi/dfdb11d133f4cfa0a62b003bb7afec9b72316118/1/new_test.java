@Test
    public void testHexStringToString() {
        assertEquals("ABC", ParseUtil.hexStringToString("414243"));
        assertEquals("ab00cd", ParseUtil.hexStringToString("ab00cd"));
        assertEquals("ab88cd", ParseUtil.hexStringToString("ab88cd"));
        assertEquals("notHex", ParseUtil.hexStringToString("notHex"));
        assertEquals("320", ParseUtil.hexStringToString("320"));
        assertEquals("0", ParseUtil.hexStringToString("0"));
    }