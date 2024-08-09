@Test
  public void assertEquals() {
    query(_UNIT_ASSERT_EQUALS.args("1", "1"), "");
    error(_UNIT_ASSERT_EQUALS.args("1", "2"), Err.UNIT_ASSERT_EQUALS);
  }