@Test
public void writeTextLines() {
  error(_FILE_WRITE_TEXT_LINES.args(PATH, "x"), Err.FILE_ID);
  error(_FILE_WRITE_TEXT_LINES.args(PATH1, " 123"), Err.INVCAST);

  query(_FILE_WRITE_TEXT_LINES.args(PATH1, "x"));
  query(_FILE_SIZE.args(PATH1), 1 + Prop.NL.length());
  query(_FILE_WRITE_TEXT_LINES.args(PATH1, "\u00fc", "US-ASCII"));
  query(_FILE_READ_TEXT_LINES.args(PATH1), "?");
  query(_FILE_DELETE.args(PATH1));
}