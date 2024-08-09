@Test
  public void get() {
    query(_MAP_GET.args(" map{}", 1), "");
    query(_MAP_GET.args(_MAP_ENTRY.args(1, 2), 1), 2);
  }