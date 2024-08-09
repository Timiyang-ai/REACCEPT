@Test
  public void cache() {
    final Function func = _LAZY_CACHE;
    query(_FILE_READ_TEXT.args(FILE), "<");
    query(func.args(_FILE_READ_BINARY.args(FILE)), "<");
    query(func.args(_FILE_READ_TEXT.args(FILE)), "<");
  }