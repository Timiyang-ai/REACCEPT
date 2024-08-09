    @Test
    public void hasFont() {
        assertThat(foobarText, TextMatchers.hasFont(Font.getDefault()));
        assumeThat("skipping: no testable fonts installed on system", fontFamily, is(notNullValue()));
        assertThat(quuxText, TextMatchers.hasFont(Font.font(fontFamily, 16)));
    }