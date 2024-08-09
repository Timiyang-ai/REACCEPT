@Test
    public void testStringToByteArray() {
        byte[] temp = { (byte) '1', (byte) '2', (byte) 'a', (byte) 'f', (byte) 0 };
        assertTrue(Arrays.equals(temp, ParseUtil.stringToByteArray("12af", 5)));
    }