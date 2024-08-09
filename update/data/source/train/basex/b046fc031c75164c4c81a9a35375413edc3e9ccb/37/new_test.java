@Test
  public void replace() {
    // tests for issue GH-573:
    query("replace('aaaa bbbbbbbb ddd ','(.{6,15}) ','$1@')", "aaaa bbbbbbbb@ddd ");
    query("replace(' aaa AAA 123','(\\s+\\P{Ll}{3,280}?)','$1@')", " aaa AAA@ 123@");
    error("replace('asdf','a{12,3}','')", Err.REGPAT_X);
  }