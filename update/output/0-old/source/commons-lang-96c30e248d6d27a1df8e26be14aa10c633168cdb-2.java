@Test
    public void testNextLong() throws Exception {
        long result = RandomUtils.nextLong(33L, 42L);
        assertTrue(result >= 33L && result < 42L);
    }