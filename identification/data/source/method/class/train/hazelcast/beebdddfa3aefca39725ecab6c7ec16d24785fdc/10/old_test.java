    @Test
    public void test_commitIndex() {
        int ix = 123;
        state.commitIndex(ix);
        assertEquals(ix, state.commitIndex());
    }