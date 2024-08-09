@Test
  public void drop() {
    // non-existing DB name
    final String dbname = NAME + "DBCreate";

    // drop existing DB
    query(_DB_CREATE.args(dbname, "<dummy/>", "doc.xml"));
    query(_DB_DROP.args(dbname));
    query(_DB_EXISTS.args(dbname), "false");

    // try to drop non-existing DB
    error(_DB_DROP.args(dbname), Err.BXDB_WHICH);
  }