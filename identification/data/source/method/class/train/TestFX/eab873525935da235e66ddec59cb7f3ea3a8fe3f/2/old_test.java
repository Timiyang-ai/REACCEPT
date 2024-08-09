    @Test
    public void isColor() {
        assertThat(Color.color(1, 0, 0), ColorMatchers.isColor(Color.RED));
    }