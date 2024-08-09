@Test
  public void rename() throws BaseXException {
    new Add("test/docs", FLDR).execute(context);
    query(COUNT.args(COLLECTION.args(NAME + "/test")), NFLDR);

    // rename document
    query(_DB_RENAME.args(NAME, "test", "newtest"));
    query(COUNT.args(COLLECTION.args(NAME + "/test")), 0);
    query(COUNT.args(COLLECTION.args(NAME + "/newtest")), NFLDR);

    // rename paths
    query(_DB_RENAME.args(NAME, "", "x"));
    query(COUNT.args(COLLECTION.args(NAME + "/x/newtest")), NFLDR);

    // rename binary file
    query(_DB_STORE.args(NAME, "one", ""));
    query(_DB_RENAME.args(NAME, "one", "two"));
    query(_DB_RETRIEVE.args(NAME, "two"));
    error(_DB_RETRIEVE.args(NAME, "one"), Err.WHICHRES);

    // invalid target
    error(_DB_RENAME.args(NAME, "x/input.xml", " ''"), Err.BXDB_RENAME);
    error(_DB_RENAME.args(NAME, "x/input.xml", " '/'"), Err.BXDB_RENAME);
    error(_DB_RENAME.args(NAME, "x/input.xml", " '.'"), Err.BXDB_RENAME);
  }