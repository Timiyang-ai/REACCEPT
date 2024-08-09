@Test
  public void retrieve() {
    error(_DB_RETRIEVE.args(NAME, "raw"), Err.WHICHRES_X);
    query(_DB_STORE.args(NAME, "raw", "xs:hexBinary('41')"));
    query("xs:hexBinary(" + _DB_RETRIEVE.args(NAME, "raw") + ')', "41");
    query(_DB_DELETE.args(NAME, "raw"));
    error(_DB_RETRIEVE.args(NAME, "raw"), Err.WHICHRES_X);
  }