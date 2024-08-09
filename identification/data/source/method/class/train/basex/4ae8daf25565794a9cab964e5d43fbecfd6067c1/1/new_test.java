@Test
  public void time() {
    final Function func = _PROF_TIME;
    query(func.args(" ()"));
    query("count(" + func.args(" 1 to 100 ") + ")", 100);
    query("count(" + func.args(" 1 to 100 ", "label") + ")", 100);
  }