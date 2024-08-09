@Test
  public void assrt() {
    query(_UNIT_ASSERT.args("1"), "");
    query(_UNIT_ASSERT.args("(<a/>,<b/>)"), "");
    error(_UNIT_ASSERT.args("()"), Err.UNIT_ASSERT);
    error(_UNIT_ASSERT.args("()", "X"), Err.UNIT_MESSAGE_X);
  }