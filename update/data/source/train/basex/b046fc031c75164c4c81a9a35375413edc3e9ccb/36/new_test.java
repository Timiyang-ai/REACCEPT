@Test
  public void open() throws BaseXException {
    query(COUNT.args(_DB_OPEN.args(NAME)), "1");
    query(COUNT.args(_DB_OPEN.args(NAME, "")), "1");
    query(COUNT.args(_DB_OPEN.args(NAME, "unknown")), "0");

    // close database instance
    new Close().execute(context);
    query(COUNT.args(_DB_OPEN.args(NAME, "unknown")), "0");
    query(_DB_OPEN.args(NAME) + "//title/text()", "XML");

    // reference invalid path
    if(Prop.WIN) error(_DB_OPEN.args(NAME, "*"), Err.RESINV_X);

    // run function on non-existing database
    new DropDB(NAME).execute(context);
    error(_DB_OPEN.args(NAME), Err.BXDB_OPEN_X);
  }