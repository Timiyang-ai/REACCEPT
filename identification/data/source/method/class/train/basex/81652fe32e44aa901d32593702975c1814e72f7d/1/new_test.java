@Test
  public void put() {
    // no entry
    count(_MAP_PUT.args(" map{}", 1, 2), 1);
    count(_MAP_PUT.args(" map{}", "a", "b"), 1);
    count(_MAP_PUT.args(" map{ 'a': 'b' }", "c", "d"), 2);
    count(_MAP_PUT.args(" map{ 'a': 'b' }", "c", "d"), 2);

    query(_MAP_PUT.args(" map{ xs:time('01:01:01'):'b' }", "xs:time('01:01:02+01:00')", "1"));
  }