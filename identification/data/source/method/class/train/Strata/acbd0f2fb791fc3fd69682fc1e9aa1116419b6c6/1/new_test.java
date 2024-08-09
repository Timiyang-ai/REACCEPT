  @Test
  public void test_generate_padData() {
    List<AsciiTableAlignment> alignments = ImmutableList.of(AsciiTableAlignment.LEFT, AsciiTableAlignment.RIGHT);
    List<String> headers = ImmutableList.of("Alpha", "Beta");
    List<List<String>> cells = ImmutableList.of(ImmutableList.of("12", "23"), ImmutableList.of("12345", ""));
    String test = AsciiTable.generate(headers, alignments, cells);
    String expected = "" +
        "+-------+------+" + LINE_SEPARATOR +
        "| Alpha | Beta |" + LINE_SEPARATOR +
        "+-------+------+" + LINE_SEPARATOR +
        "| 12    |   23 |" + LINE_SEPARATOR +
        "| 12345 |      |" + LINE_SEPARATOR +
        "+-------+------+" + LINE_SEPARATOR;
    assertThat(test).isEqualTo(expected);
  }