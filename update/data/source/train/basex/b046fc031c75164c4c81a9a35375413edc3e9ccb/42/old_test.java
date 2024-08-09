@Test
  public void unparsedText() {
    contains(UNPARSED_TEXT.args(TEXT), "&lt;html");
    contains(UNPARSED_TEXT.args(TEXT, "US-ASCII"), "&lt;html");
    error(UNPARSED_TEXT.args(TEXT, "xyz"), Err.WHICHENC);
  }