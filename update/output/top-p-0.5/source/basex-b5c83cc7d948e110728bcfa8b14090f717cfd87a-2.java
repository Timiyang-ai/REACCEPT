@Test
  public void writeTextLines() {
    error(_FILE_WRITE_TEXT_LINES.args(PATH, "x"), Err.FILE_ID); // Updated error check based on sample diff
    error(_FILE_WRITE_TEXT_LINES.args(PATH1, " 123"), Err.INVCAST);

    query(_FILE_WRITE_TEXT_LINES.args(PATH1, "x"));
    query(_FILE_SIZE.args(PATH1), 1 + Prop.NL.length());
    query(_FILE_WRITE_TEXT_LINES.args(PATH1, "\u00fc", "US-ASCII")); // Assuming the change in encoding handling doesn't affect this directly
    query(_FILE_READ_TEXT_LINES.args(PATH1), "?");
    query(_FILE_DELETE.args(PATH1));
  }