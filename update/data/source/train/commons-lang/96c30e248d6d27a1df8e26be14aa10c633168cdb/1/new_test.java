@Test
    public void testNextDouble() throws Exception {
        final double result = RandomUtils.nextDouble(33d, 42d);
        assertTrue(result >= 33d && result <= 42d);
    }