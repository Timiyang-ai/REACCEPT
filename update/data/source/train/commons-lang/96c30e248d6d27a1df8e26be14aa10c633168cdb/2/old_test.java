@Test
    public void testNextFloat() throws Exception {
        double result = RandomUtils.nextFloat(33f, 42f);
        assertTrue(result >= 33f && result <= 42f);
    }