    @Test
    public void parseCoord() throws Exception {
        List<String> args = ImmutableList.of("G10", "G3", "X100", "y-.5", "Z0.25");
        assertThat(GcodePreprocessorUtils.parseCoord(args, 'x')).isEqualTo(100);
        assertThat(GcodePreprocessorUtils.parseCoord(args, 'y')).isEqualTo(-0.5);
        assertThat(GcodePreprocessorUtils.parseCoord(args, 'z')).isEqualTo(0.25);

        assertThat(GcodePreprocessorUtils.parseCoord(args, 'X')).isEqualTo(100);
        assertThat(GcodePreprocessorUtils.parseCoord(args, 'Y')).isEqualTo(-0.5);
        assertThat(GcodePreprocessorUtils.parseCoord(args, 'Z')).isEqualTo(0.25);
    }