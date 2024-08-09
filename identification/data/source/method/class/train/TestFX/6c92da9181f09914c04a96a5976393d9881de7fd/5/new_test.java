    @Test
    public void hasDimension() {
        assertThat(new Dimension2D(10, 20), GeometryMatchers.hasDimension(10, 20));
    }