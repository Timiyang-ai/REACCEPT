@Test
    public void testHexStringToByteArray() {
        byte[] temp = { (byte) 0x12, (byte) 0xaf };
        assertTrue(Arrays.equals(temp, ParseUtil.hexStringToByteArray("12af")));
        temp = new byte[0];
        assertTrue(Arrays.equals(temp, ParseUtil.hexStringToByteArray("expected error abcde")));
        assertTrue(Arrays.equals(temp, ParseUtil.hexStringToByteArray("abcde")));
    }