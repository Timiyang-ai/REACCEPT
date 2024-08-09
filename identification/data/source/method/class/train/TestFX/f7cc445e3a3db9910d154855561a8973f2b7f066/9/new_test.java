    @Test
    public void hasFontSmoothingType() {
        assertThat(foobarText, TextMatchers.hasFontSmoothingType(FontSmoothingType.GRAY));
        assertThat(quuxText, TextMatchers.hasFontSmoothingType(FontSmoothingType.LCD));
    }