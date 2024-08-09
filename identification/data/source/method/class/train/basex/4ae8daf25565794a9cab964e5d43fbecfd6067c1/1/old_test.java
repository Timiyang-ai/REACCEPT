@Test
  public void time() {
    query(_PROF_TIME.args("()"));
    query("count(" + _PROF_TIME.args(" 1 to 100 ", false) + ")", 100);
    query("count(" + _PROF_TIME.args(" 1 to 100 ", true) + ")", 100);
    query("count(" + _PROF_TIME.args(" 1 to 100 ", true, "label") + ")", 100);
  }