@Test
  public void foldLeft1Test() {
    query("hof:fold-left1(1 to 10, function($x, $y) { $x + $y })", "55");
    error("hof:fold-left1((), function($x, $y) { $x + $y })", Err.INVEMPTY);
  }