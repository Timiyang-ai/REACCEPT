@Test
    public void test_toMillis() {
        assertEquals(Duration.ofSeconds(321, 123456789).toMillis(), 321000 + 123);
        assertEquals(Duration.ofMillis(Long.MAX_VALUE).toMillis(), 9223372036854775807L);
        assertEquals(Duration.ofMillis(Long.MIN_VALUE).toMillis(), -9223372036854775808L);
    }