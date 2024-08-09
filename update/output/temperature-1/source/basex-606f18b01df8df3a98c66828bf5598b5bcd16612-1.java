@Test
public void readBinary() {
  // check errors
  error(_FILE_READ_BINARY.args(PATH1), Err.FILE_NOT_FOUND); // Error names updated to match new error handling
  error(_FILE_READ_BINARY.args(PATH), Err.FILE_IS_DIR); // Updated error for directory check
 
  // file with single codepoint, write and then read testing for expected base64 encoded output
  query(_FILE_WRITE.args(PATH1, "0"));
  query(_FILE_READ_BINARY.args(PATH1), "MA==");
  query(_FILE_READ_BINARY.args(PATH1, 0), "MA==");
  query(_FILE_READ_BINARY.args(PATH1, 0, 1), "MA==");
  query(_FILE_READ_BINARY.args(PATH1, 1), "");
  query(_FILE_READ_BINARY.args(PATH1, 1, 0), "");
  query(_FILE_READ_BINARY.args(PATH1, 0, 0), "");
  
  // testing out of range scenarios with the updated FILE_OUT_OF_RANGE error
  error(_FILE_READ_BINARY.args(PATH1, -1), Err.FILE_OUT_OF_RANGE);
  error(_FILE_READ_BINARY.args(PATH1, 2), Err.FILE_OUT_OF_RANGE);
  error(_FILE_READ_BINARY.args(PATH1, 0, -1), Err.FILE_OUT_OF_RANGE);
  error(_FILE_READ_BINARY.args(PATH1, 0, 2), Err.FILE_OUT_OF_RANGE);
  error(_FILE_READ_BINARY.args(PATH1, 2, 1), Err.FILE_OUT_OF_RANGE);
  
  // file with two codepoints, write in plain and then binary with base64 encoding, read expected base64 output
  query(_FILE_WRITE.args(PATH1, "a\u00e4"));
  query(_FILE_READ_BINARY.args(PATH1), "YcOk");
  query(_FILE_WRITE_BINARY.args(PATH1, _CONVERT_STRING_TO_BASE64.args("a\u00e4")));
  query(_FILE_READ_BINARY.args(PATH1), "YcOk");
  
  // delete file after tests
  query(_FILE_DELETE.args(PATH1));
}