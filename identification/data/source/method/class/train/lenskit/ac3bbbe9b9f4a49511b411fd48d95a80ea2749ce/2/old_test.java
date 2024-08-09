@Test
    public void testMean() {
        assertThat(emptyVector().mean(), closeTo(0));
        assertThat(singleton().mean(), closeTo(Math.PI));
        assertThat(simpleVector().mean(), closeTo(7.0 / 3));
    }