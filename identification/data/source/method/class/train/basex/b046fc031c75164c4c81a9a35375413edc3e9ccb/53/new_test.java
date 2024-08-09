@Test
  public void text() {
    query(_FETCH_TEXT.args(FILE));
    error(_FETCH_TEXT.args(FILE + 'x'), Err.BXFE_IO_X);
    error(_FETCH_TEXT.args(FILE, "xxx"), Err.BXFE_ENCODING_X);
  }