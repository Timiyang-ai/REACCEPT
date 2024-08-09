@Test
    public void testNorm() {
        assertThat(emptyVector().norm(), closeTo(0));
        assertThat(singleton().norm(), closeTo(Math.PI));
        SparseVector sv = simpleVector();
        assertThat(sv.norm(), closeTo(4.301162634));
        assertThat(sv.norm(), closeTo(4.301162634));  // doubled, to check caching
    }