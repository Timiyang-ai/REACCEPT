@Test
  public void dump() {
    try {
      System.setErr(NULL);
      query(_PROF_DUMP.args("a"), "");
    } finally {
      System.setErr(ERR);
    }
  }