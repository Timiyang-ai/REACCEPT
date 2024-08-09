@Test
    public void testHexStringToByteArray() {
        byte[] temp = { (byte) 0x12, (byte) 0xaf };
        assertTrue(Arrays.equals(temp, ParseUtil.hexStringToByteArray("12af")));
        assertEquals(null, ParseUtil.hexStringToByteArray("abcde"));
        assertEquals(null, ParseUtil.hexStringToByteArray("not hex"));
    }