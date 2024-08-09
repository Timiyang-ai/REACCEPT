@Test
  public void entry() {
    query(EXISTS.args(_MAP_ENTRY.args("A", "B")), true);
    query(EXISTS.args(_MAP_ENTRY.args(1, 2)), true);
    query(EXISTS.args(_MAP_MERGE.args(_MAP_ENTRY.args(1, 2))), "true");
    error(EXISTS.args(_MAP_ENTRY.args("()", 2)), Err.EMPTYFOUND);
    error(EXISTS.args(_MAP_ENTRY.args("(1,2)", 2)), Err.SEQFOUND_X);
  }