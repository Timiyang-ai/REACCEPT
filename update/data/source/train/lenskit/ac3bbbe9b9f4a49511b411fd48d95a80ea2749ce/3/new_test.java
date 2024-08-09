@Test
    public void testSum() {
        assertThat(emptyVector().sum(), closeTo(0));
        assertThat(singleton().sum(), closeTo(Math.PI));
        SparseVector sv = simpleVector();
        assertThat(sv.sum(), closeTo(7));
        assertThat(sv.sum(), closeTo(7)); // doubled, to check caching
    }