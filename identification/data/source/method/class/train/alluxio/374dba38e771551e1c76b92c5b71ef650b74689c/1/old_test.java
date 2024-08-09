  @Test
  public void stripLeadingAndTrailingQuotes() throws Exception {
    assertEquals("", CommonUtils.stripLeadingAndTrailingQuotes(""));
    assertEquals("\"", CommonUtils.stripLeadingAndTrailingQuotes("\""));
    assertEquals("", CommonUtils.stripLeadingAndTrailingQuotes("\"\""));
    assertEquals("\"", CommonUtils.stripLeadingAndTrailingQuotes("\"\"\""));
    assertEquals("\"\"", CommonUtils.stripLeadingAndTrailingQuotes("\"\"\"\""));
    assertEquals("noquote", CommonUtils.stripLeadingAndTrailingQuotes("noquote"));
    assertEquals(
        "\"singlequote", CommonUtils.stripLeadingAndTrailingQuotes("\"singlequote"));
    assertEquals(
        "singlequote\"", CommonUtils.stripLeadingAndTrailingQuotes("singlequote\""));
    assertEquals("quoted", CommonUtils.stripLeadingAndTrailingQuotes("\"quoted\""));
    assertEquals("\"quoted\"", CommonUtils.stripLeadingAndTrailingQuotes("\"\"quoted\"\""));
  }