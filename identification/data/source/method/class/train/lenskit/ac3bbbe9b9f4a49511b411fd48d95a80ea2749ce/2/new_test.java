@Test
    public void testMean() {
        assertThat(emptyVector().mean(), closeTo(0));
        assertThat(singleton().mean(), closeTo(Math.PI));
        SparseVector sv = simpleVector();
        assertThat(sv.mean(), closeTo(7.0 / 3));
        assertThat(sv.mean(), closeTo(7.0 / 3));  // doubled, to check caching
    }