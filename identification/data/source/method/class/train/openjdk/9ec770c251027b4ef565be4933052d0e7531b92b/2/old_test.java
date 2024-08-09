@Test
    public void test_toNanos() {
        Duration test = Duration.ofSeconds(321, 123456789);
        assertEquals(test.toNanos(), 321123456789L);
    }