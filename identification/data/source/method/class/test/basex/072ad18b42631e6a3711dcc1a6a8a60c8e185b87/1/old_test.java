@Test
  public void assrt() {
    query(_XQUNIT_ASSERT.args("1"), "");
    query(_XQUNIT_ASSERT.args("(<a/>,<b/>)"), "");
    error(_XQUNIT_ASSERT.args("()"), Err.BXUN_ASSERT);
    error(_XQUNIT_ASSERT.args("()", "X"), Err.BXUN_ERROR);
  }