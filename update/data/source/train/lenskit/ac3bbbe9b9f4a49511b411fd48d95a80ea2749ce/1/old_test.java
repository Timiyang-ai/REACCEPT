@Test
    public void testNorm() {
        assertThat(emptyVector().norm(), closeTo(0));
        assertThat(singleton().norm(), closeTo(Math.PI));
        assertThat(simpleVector().norm(), closeTo(4.301162634));
    }