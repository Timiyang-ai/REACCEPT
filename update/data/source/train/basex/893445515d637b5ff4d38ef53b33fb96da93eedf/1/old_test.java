@Test
  public void put() {
    // no entry
    count(_MAP_PUT.args(" map{}", 1, 2), 1);
    count(_MAP_PUT.args(" map{}", "a", "b"), 1);
    count(_MAP_PUT.args(" map{ 'a': 'b' }", "c", "d"), 2);
    count(_MAP_PUT.args(" map{ 'a': 'b' }", "c", "d"), 2);
  }