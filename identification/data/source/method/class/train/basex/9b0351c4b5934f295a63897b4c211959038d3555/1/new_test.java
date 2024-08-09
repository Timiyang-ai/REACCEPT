@Test
  public void testCount() throws QueryException {
    final String fun = check(Function.FTCOUNT);
    query(fun + "(())", "0");
    query(fun + "(//*[text() contains text '1'])", "1");
    query(fun + "(//li[text() contains text 'exercise'])", "2");
    query("for $i in //li[text() contains text 'exercise'] " +
        "return " + fun + "($i[text() contains text 'exercise'])", "1 1");
  }