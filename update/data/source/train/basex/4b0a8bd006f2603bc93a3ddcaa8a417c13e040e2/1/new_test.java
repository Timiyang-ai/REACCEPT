@Test
  public void foldLeft1Test() {
    query("hof:fold-left1(1 to 10, function($x, $y) { $x + $y })", "55");
    error("hof:fold-left1((), function($x, $y) { $x + $y })", Err.SEQEMPTY);
    // should be unrolled and evaluated at compile time
    check("hof:fold-left1(1 to 9, function($a,$b) {$a+$b})",
        "45",
        "empty(//" + Util.className(FNHof.class) + "[contains(@name, 'fold-left1')])",
        "exists(*/" + Util.className(Int.class) + ')');
    // should be unrolled but not evaluated at compile time
    check("hof:fold-left1(1 to 9, function($a,$b) {0*random:integer($a)+$b})",
        "9",
        "empty(//" + Util.className(FNHof.class) + "[contains(@name, 'fold-left1')])",
        "empty(*/" + Util.className(Int.class) + ')',
        "count(//" + Util.className(Arith.class) + "[@op = '+']) eq 8");
    // should not be unrolled
    check("hof:fold-left1(1 to 10, function($a,$b) {$a+$b})",
        "55",
        "exists(//" + Util.className(FNHof.class) + "[contains(@name, 'fold-left1')])");
  }