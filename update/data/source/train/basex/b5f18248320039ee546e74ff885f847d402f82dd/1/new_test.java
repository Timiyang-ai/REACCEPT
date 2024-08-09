@Test
  public void get() {
    final Function func = _MAP_GET;
    query(func.args(" map{}", 1), "");
    query(func.args(_MAP_ENTRY.args(1, 2), 1), 2);
  }