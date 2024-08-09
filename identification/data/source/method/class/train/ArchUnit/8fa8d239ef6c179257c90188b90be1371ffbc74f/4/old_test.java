    @Test
    @UseDataProvider("base_locations")
    public void append(Location location) {
        Location appendAbsolute = location.append("/bar/baz");
        Location appendRelative = location.append("bar/baz");

        Location expected = Location.of(Paths.get("/some/path/bar/baz"));
        assertThat(appendAbsolute).isEqualTo(expected);
        assertThat(appendRelative).isEqualTo(expected);
    }