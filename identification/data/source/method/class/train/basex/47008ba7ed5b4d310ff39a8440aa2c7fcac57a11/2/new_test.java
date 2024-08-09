@Test
  public void open() {
    query("count(" + _DB_OPEN.args(NAME) + ")", 1);
    query("count(" + _DB_OPEN.args(NAME, "") + ")", 1);
    query("count(" + _DB_OPEN.args(NAME, "unknown") + ")", 0);

    // close database instance
    execute(new Close());
    query("count(" + _DB_OPEN.args(NAME, "unknown") + ")", 0);
    query(_DB_OPEN.args(NAME) + "//title/text()", "XML");

    // reference invalid path
    if(Prop.WIN) error(_DB_OPEN.args(NAME, "*"), RESINV_X);

    // run function on non-existing database
    execute(new DropDB(NAME));
    error(_DB_OPEN.args(NAME), DB_OPEN2_X);
  }