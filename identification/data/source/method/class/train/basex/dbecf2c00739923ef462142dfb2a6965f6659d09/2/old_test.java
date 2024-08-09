@Test
  public void content() {
    query(_FETCH_CONTENT.args(FILE));
    error(_FETCH_CONTENT.args(FILE + 'x'), Err.BXFE_IO);
    error(_FETCH_CONTENT.args(FILE, "xxx"), Err.BXFE_ENCODING);
  }