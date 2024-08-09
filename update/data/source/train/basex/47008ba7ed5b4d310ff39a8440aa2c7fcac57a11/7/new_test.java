@Test
  public void copy() {
    // close database in global context
    execute(new Close());

    // copy database to new name and vice versa
    query(_DB_COPY.args(NAME, NAME + 'c'));
    try {
      query(_DB_COPY.args(NAME + 'c', NAME));
    } finally {
      query(_DB_DROP.args(NAME + 'c'));
    }

    // invalid names
    error(_DB_COPY.args("x", " ''"), DB_NAME_X);
    error(_DB_COPY.args(" ''", "x"), DB_NAME_X);

    // same name is disallowed
    error(_DB_COPY.args(NAME, NAME), DB_CONFLICT4_X);
    // source database does not exist
    error(_DB_COPY.args(NAME + "copy", NAME), DB_OPEN1_X);
  }