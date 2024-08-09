@Test
  public void text() {
    query(_FETCH_TEXT.args(FILE));
    error(_FETCH_TEXT.args(FILE + 'x'), Err.BXFE_IO);
    error(_FETCH_TEXT.args(FILE, "xxx"), Err.BXFE_ENCODING);
  }