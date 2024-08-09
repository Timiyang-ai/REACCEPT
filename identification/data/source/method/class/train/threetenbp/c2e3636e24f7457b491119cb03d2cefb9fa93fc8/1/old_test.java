@Test(groups={"tck"})
    public void test_toNanos() {
        Duration test = Duration.ofSeconds(321, 123456789);
        assertEquals(test.toNanos(), BigInteger.valueOf(321123456789L));
    }