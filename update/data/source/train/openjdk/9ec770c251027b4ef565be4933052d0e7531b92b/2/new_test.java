@Test
    public void test_toNanos() {
        assertEquals(Duration.ofSeconds(321, 123456789).toNanos(), 321123456789L);
        assertEquals(Duration.ofNanos(Long.MAX_VALUE).toNanos(), 9223372036854775807L);
        assertEquals(Duration.ofNanos(Long.MIN_VALUE).toNanos(), -9223372036854775808L);
    }