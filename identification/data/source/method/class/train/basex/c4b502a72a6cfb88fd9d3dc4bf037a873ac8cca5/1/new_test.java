@Test
  public void sum() {
    query("sum(1)", "1");
    query("sum(1 to 10)", "55");
    query("sum(1 to 3037000499)", "4611686016981624750");
    query("sum(1 to 3037000500)", "4611686020018625250");
    query("sum(1 to 4294967295)", "9223372034707292160");
    query("sum((), ())", "");
    error("sum(1, 'x')", SUM_X_X);
    error("sum((), (1,2))", SEQFOUND_X);
  }