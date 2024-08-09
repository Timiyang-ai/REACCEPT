@Test
    public void testNextBytes() throws Exception {
        final byte[] result = RandomUtils.nextBytes(20);
        assertEquals(20, result.length);
    }