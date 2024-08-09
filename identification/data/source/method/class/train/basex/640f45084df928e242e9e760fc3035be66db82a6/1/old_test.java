@Test
  public void writeBinary() {
    final String bin = "xs:base64Binary('MA==')";
    error(_FILE_WRITE_BINARY.args(PATH, bin), Err.FILE_DIR);
    error(_FILE_WRITE_BINARY.args(PATH1, "NoBinary"), Err.BINARYTYPE);

    query(_FILE_WRITE_BINARY.args(PATH1, bin));
    query(_FILE_SIZE.args(PATH1), "1");
    query(_FILE_WRITE_BINARY.args(PATH1, bin));
    query(_FILE_SIZE.args(PATH1), "1");
    query(_FILE_DELETE.args(PATH1));
  }