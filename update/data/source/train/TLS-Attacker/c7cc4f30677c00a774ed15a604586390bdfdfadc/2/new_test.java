@Test
    public void testCalculateMac() {
        assertArrayEquals(record.calculateMac(data), new byte[0]);
    }