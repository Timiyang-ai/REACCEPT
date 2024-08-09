@Test
    public void testSum() {
        assertThat(emptyVector().sum(), closeTo(0));
        assertThat(singleton().sum(), closeTo(Math.PI));
        assertThat(simpleVector().sum(), closeTo(7));
    }