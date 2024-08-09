@Test(expected = UnsupportedOperationException.class)
    public void testCalculateMac() {
        record.calculateMac(data);
    }