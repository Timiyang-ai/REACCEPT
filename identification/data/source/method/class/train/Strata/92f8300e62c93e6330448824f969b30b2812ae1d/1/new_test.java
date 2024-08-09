  @Test
  public void test_writeCell() {
    StringBuilder buf = new StringBuilder();
    CsvOutput.standard(buf, "\n")
        .writeCell("a")
        .writeCell("x")
        .writeNewLine()
        .writeCell("b", true)
        .writeCell("y", true)
        .writeNewLine();
    assertThat(buf.toString()).isEqualTo("a,x\n\"b\",\"y\"\n");
  }