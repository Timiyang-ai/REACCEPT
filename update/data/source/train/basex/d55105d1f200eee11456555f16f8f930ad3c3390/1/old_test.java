@Test
  public void swallow() {
    query(_PROF_SWALLOW.args("()"), "");
    query(_PROF_SWALLOW.args("1"), "");
    query(_PROF_SWALLOW.args("1,2"), "");
  }