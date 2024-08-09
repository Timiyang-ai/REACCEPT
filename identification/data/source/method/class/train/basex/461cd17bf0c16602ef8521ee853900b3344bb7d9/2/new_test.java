@Test
  public void restore() throws BaseXException {
    new Close().execute(context);

    // backup and restore file
    query(_DB_CREATE_BACKUP.args(NAME));
    query(_DB_RESTORE.args(NAME));

    // [LK] more tests! E.g. combination of restore, create, ...
  }