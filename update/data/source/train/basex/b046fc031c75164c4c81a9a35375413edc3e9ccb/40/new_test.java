@Test
  public void integerToBase() {
    query(_CONVERT_INTEGER_TO_BASE.args(4, 2), 100);
    query(_CONVERT_INTEGER_TO_BASE.args(65535, 2), "1111111111111111");
    query(_CONVERT_INTEGER_TO_BASE.args(65536, 2), "10000000000000000");
    query(_CONVERT_INTEGER_TO_BASE.args(4, 16), 4);
    query(_CONVERT_INTEGER_TO_BASE.args(65535, 16), "ffff");
    query(_CONVERT_INTEGER_TO_BASE.args(65536, 16), "10000");
    query(_CONVERT_INTEGER_TO_BASE.args(4, 10), 4);
    query(_CONVERT_INTEGER_TO_BASE.args(65535, 10), 65535);
    query(_CONVERT_INTEGER_TO_BASE.args(65536, 10), 65536);
    error(_CONVERT_INTEGER_TO_BASE.args(1, 1), Err.INVBASE_X);
    error(_CONVERT_INTEGER_TO_BASE.args(1, 100), Err.INVBASE_X);
    error(_CONVERT_INTEGER_TO_BASE.args(1, 100), Err.INVBASE_X);
  }