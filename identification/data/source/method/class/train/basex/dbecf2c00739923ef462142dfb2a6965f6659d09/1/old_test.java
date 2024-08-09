@Test
  public void contentBinary() {
    query(_FETCH_CONTENT_BINARY.args(FILE));
    error(_FETCH_CONTENT_BINARY.args(FILE + 'x'), Err.BXFE_IO);
  }