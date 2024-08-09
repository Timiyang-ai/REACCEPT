    @Test
    public void test_lastApplied() {
        int last = 123;
        state.lastApplied(last);
        assertEquals(last, state.lastApplied());
    }