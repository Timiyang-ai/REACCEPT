@Test
  public void optimize() throws BaseXException {
    query(_DB_OPTIMIZE.args(NAME));
    query(_DB_OPTIMIZE.args(NAME));
    error(_DB_OPTIMIZE.args(NAME, "true()"), Err.UPDBOPTERR);
    new Close().execute(context);
    query(_DB_OPTIMIZE.args(NAME, "true()"));
  }