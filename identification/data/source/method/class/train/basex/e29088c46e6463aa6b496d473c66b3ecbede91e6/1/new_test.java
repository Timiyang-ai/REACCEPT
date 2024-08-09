@Test
  public void collation() {
    query(_MAP_COLLATION.args(_MAP_NEW.args()), Token.string(QueryText.COLLATIONURI));
  }