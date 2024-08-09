    @Test
    public void hasClosestNamedColor_color() {
        assertThat(Color.color(0.8, 0.2, 0.1), ColorMatchers.hasClosestNamedColor(Color.FIREBRICK));
    }