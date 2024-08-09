@Test
  public void type() {
    try {
      System.setErr(NULL);
      query(_XQUERY_TYPE.args("()"), "");
      query(_XQUERY_TYPE.args("1"), "1");
      query(_XQUERY_TYPE.args("(1, 2, 3)"), "1\n2\n3");
      query(_XQUERY_TYPE.args("<x a='1' b='2' c='3'/>/@*/data()"), "1\n2\n3");
    } finally {
      System.setErr(ERR);
    }
  }