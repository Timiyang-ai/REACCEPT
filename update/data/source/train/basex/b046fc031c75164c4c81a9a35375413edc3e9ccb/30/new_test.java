@Test
  public void binary() {
    query(_FETCH_BINARY.args(FILE));
    error(_FETCH_BINARY.args(FILE + 'x'), Err.BXFE_IO_X);
  }