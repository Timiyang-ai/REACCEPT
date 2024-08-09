@Test
  public void copy() throws BaseXException {
    // close database in global context
    new Close().execute(context);

    // copy database to new name and vice versa
    query(_DB_COPY.args(NAME, NAME + 'x'));
    try {
      query(_DB_COPY.args(NAME + 'x', NAME));
    } finally {
      query(_DB_DROP.args(NAME + 'x'));
    }

    // invalid names
    error(_DB_COPY.args("x", " ''"), Err.BXDB_NAME);
    error(_DB_COPY.args(" ''", "x"), Err.BXDB_NAME);

    // same name is disallowed
    error(_DB_COPY.args(NAME, NAME), Err.BXDB_SAME);
    // source database does not exist
    error(_DB_COPY.args(NAME + "xx", NAME), Err.BXDB_WHICH);
  }