@Test(groups={"tck"})
    public void test_toMillisLong() {
        Duration test = Duration.ofSeconds(321, 123456789);
        assertEquals(test.toMillisLong(), 321000 + 123);
    }