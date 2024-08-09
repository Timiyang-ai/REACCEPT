  @Test
  public void test_withHeaders_writeCell() {
    List<String> headers = Arrays.asList("h1", "h2", "h3");
    StringBuilder buf = new StringBuilder();
    CsvRowOutputWithHeaders csv = CsvOutput.standard(buf).withHeaders(headers, false);
    assertThat(csv.headers()).isEqualTo(headers);
    assertThat(buf.toString()).isEqualTo("h1,h2,h3" + LINE_SEP);
    csv.writeCell("h1", "a");
    csv.writeCell("h3", "c");
    csv.writeCell("h1", "A");
    assertThat(buf.toString()).isEqualTo("h1,h2,h3" + LINE_SEP);
    csv.writeNewLine();
    assertThat(buf.toString()).isEqualTo("h1,h2,h3" + LINE_SEP + "A,,c" + LINE_SEP);
    assertThatIllegalArgumentException().isThrownBy(() -> csv.writeCell("H1", "x"));
  }