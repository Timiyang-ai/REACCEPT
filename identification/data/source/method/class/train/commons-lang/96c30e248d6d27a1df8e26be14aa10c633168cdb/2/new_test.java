@Test
    public void testNextInt() throws Exception {
        final int result = RandomUtils.nextInt(33, 42);
        assertTrue(result >= 33 && result < 42);
    }