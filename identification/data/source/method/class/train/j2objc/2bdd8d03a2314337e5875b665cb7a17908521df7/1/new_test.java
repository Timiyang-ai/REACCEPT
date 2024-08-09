    public void test_valueOf_long() throws Exception {
        assertBitSet(new long[0], "{}");
        assertBitSet(new long[] { 1L }, "{0}");
        assertBitSet(new long[] { 0x111L }, "{0, 4, 8}");
        assertBitSet(new long[] { 0x101L, 0x4000000000000000L }, "{0, 8, 126}");
    }