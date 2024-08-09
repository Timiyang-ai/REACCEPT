@Test
  public void voidd() {
    query(_PROF_VOID.args("()"), "");
    query(_PROF_VOID.args("1"), "");
    query(_PROF_VOID.args("1,2"), "");
  }