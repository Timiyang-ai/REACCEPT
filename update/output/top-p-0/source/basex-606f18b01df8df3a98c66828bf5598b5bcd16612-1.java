@Test
public void readBinaryUpdated() {
  // check errors
  error(_FILE_READ_BINARY.args(PATH1), Err.FILE_NOT_FOUND);
  error(_FILE_READ_BINARY.args(PATH), Err.FILE_IS_DIR);
  // file with single codepoint
  query(_FILE_WRITE.args(PATH1, "0"));
  query(_FILE_READ_BINARY.args(PATH1), "MA==");
  query(_FILE_READ_BINARY.args(PATH1, 0), "MA==");
  query(_FILE_READ_BINARY.args(PATH1, 0, 1), "MA==");
  query(_FILE_READ_BINARY.args(PATH1, 1), "");
  query(_FILE_READ_BINARY.args(PATH1, 1, 0), "");
  query(_FILE_READ_BINARY.args(PATH1, 0, 0), "");
  error(_FILE_READ_BINARY.args(PATH1, -1), Err.FILE_OUT_OF_RANGE);
  error(_FILE_READ_BINARY.args(PATH1, 2), Err.FILE_OUT_OF_RANGE);
  error(_FILE_READ_BINARY.args(PATH1, 0, -1), Err.FILE_OUT_OF_RANGE);
  error(_FILE_READ_BINARY.args(PATH1, 0, 2), Err.FILE_OUT_OF_RANGE);
  error(_FILE_READ_BINARY.args(PATH1, 2, 1), Err.FILE_OUT_OF_RANGE);
  // file with two codepoints
  query(_FILE_WRITE.args(PATH1, "a\u00e4"));
  query(_FILE_READ_BINARY.args(PATH1), "YcOk");
  // file with two codepoints, written as binary
  query(_FILE_WRITE_BINARY.args(PATH1, _CONVERT_STRING_TO_BASE64.args("a\u00e4")));
  query(_FILE_READ_BINARY.args(PATH1), "YcOk");
  // delete file
  query(_FILE_DELETE.args(PATH1));
}