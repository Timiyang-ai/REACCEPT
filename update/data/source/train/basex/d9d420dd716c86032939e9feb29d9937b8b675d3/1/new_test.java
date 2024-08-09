@Test
  public void sortWithTest() {
    query("hof:sort-with((), function($a, $b) { $a < $b })", "");
    query("hof:sort-with(1 to 5, function($a, $b) { $a > $b })", "5 4 3 2 1");
    error("hof:sort-with(1 to 5, <x/>)", Err.INVCAST);
  }