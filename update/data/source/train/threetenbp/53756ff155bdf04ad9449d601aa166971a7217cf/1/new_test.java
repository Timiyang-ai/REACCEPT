@Test(groups={"tck"})
    public void test_toMillis() {
        Duration test = Duration.ofSeconds(321, 123456789);
        assertEquals(test.toMillis(), 321000 + 123);
    }