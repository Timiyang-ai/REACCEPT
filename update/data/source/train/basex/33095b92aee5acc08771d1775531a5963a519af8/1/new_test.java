@Test
  public void db() {
    // General Functions
    ckDBs(new XQuery(_DB_INFO.args(NAME)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_LIST.args(NAME)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_LIST_DETAILS.args(NAME)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_LIST_DETAILS.args()), false, null);
    ckDBs(new XQuery(_DB_OPEN.args(NAME)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_OPEN_ID.args(NAME, 0)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_OPEN_PRE.args(NAME, 0)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_SYSTEM.args()), false, NONE);

    // Read Operations
    ckDBs(new XQuery(_DB_ATTRIBUTE.args(NAME, "foo")), false, NAME_LIST);
    ckDBs(new XQuery(_DB_ATTRIBUTE.args(NAME, 23, 42)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_NODE_ID.args(" .")), false, CTX_LIST);
    ckDBs(new XQuery(_DB_NODE_PRE.args(" .")), false, CTX_LIST);
    ckDBs(new XQuery(_DB_RETRIEVE.args(NAME, "foo")), false, NAME_LIST);
    ckDBs(new XQuery(_DB_TEXT.args(NAME, "foo")), false, NAME_LIST);
    ckDBs(new XQuery(_DB_TEXT_RANGE.args(NAME, 23, 42)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_TOKEN.args(NAME, "foo")), false, NAME_LIST);
    ckDBs(new XQuery(_DB_TOKEN.args(NAME, 23, 42)), false, NAME_LIST);

    // Updates
    ckDBs(new XQuery(_DB_CREATE.args(NAME)), true, NAME_LIST);
    ckDBs(new XQuery(_DB_CREATE.args(NAME, FILE)), true, NAME_LIST);
    ckDBs(new XQuery(_DB_CREATE.args(NAME, "<foo/>", FILE)), true, NAME_LIST);
    ckDBs(new XQuery(_DB_CREATE.args(NAME, FILE, FILE)), true, NAME_LIST);
    ckDBs(new XQuery(_DB_DROP.args(NAME)), true, NAME_LIST);
    ckDBs(new XQuery(_DB_ADD.args(NAME, FILE)), true, NAME_LIST);
    ckDBs(new XQuery(_DB_ADD.args(NAME, "<foo/>", FILE)), true, NAME_LIST);
    ckDBs(new XQuery(_DB_ADD.args(NAME, FILE, FILE)), true, NAME_LIST);
    ckDBs(new XQuery(_DB_DELETE.args(NAME, FILE)), true, NAME_LIST);
    ckDBs(new XQuery(_DB_OPTIMIZE.args(NAME)), true, NAME_LIST);
    ckDBs(new XQuery(_DB_OPTIMIZE.args(NAME, "true()")), true, NAME_LIST);
    ckDBs(new XQuery(_DB_RENAME.args(NAME, FILE, FILE + '2')), true, NAME_LIST);
    ckDBs(new XQuery(_DB_REPLACE.args(NAME, FILE, FILE + '2')), true, NAME_LIST);
    ckDBs(new XQuery(_DB_STORE.args(NAME, FILE, "foo")), true, NAME_LIST);
    ckDBs(new XQuery(_DB_OUTPUT.args("foo")), true, NONE);
    ckDBs(new XQuery(_DB_FLUSH.args(NAME)), true, NAME_LIST);

    // Helper Functions
    ckDBs(new XQuery(_DB_EXISTS.args(NAME)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_IS_RAW.args(NAME, FILE)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_IS_XML.args(NAME, FILE)), false, NAME_LIST);
    ckDBs(new XQuery(_DB_CONTENT_TYPE.args(NAME, FILE)), false, NAME_LIST);
  }