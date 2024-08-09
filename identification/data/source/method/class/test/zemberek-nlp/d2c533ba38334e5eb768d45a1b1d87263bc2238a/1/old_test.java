  @Test
  public void asStringTest() throws IOException {
    String a = new SimpleTextReader(multi_line_text_file.getFile()).asString();
    System.out.println(a);
  }