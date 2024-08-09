@Test
  public void sortWithTest() {
    query("hof:sort-with(function($a, $b) { $a < $b }, ())", "");
    query("hof:sort-with(function($a, $b) { $a > $b }, 1 to 5)", "5 4 3 2 1");
    error("hof:sort-with(<x/>, 1 to 5)", Err.INVCAST);
  }