@Test
  public void rename() {
    execute(new Add("test/docs", FLDR));
    query("count(" + COLLECTION.args(NAME + "/test") + ")", XMLFILES);

    // rename document
    query(_DB_RENAME.args(NAME, "test", "newtest"));
    query("count(" + COLLECTION.args(NAME + "/test") + ")", 0);
    query("count(" + COLLECTION.args(NAME + "/newtest") + ")", XMLFILES);

    // invalid target
    error(_DB_RENAME.args(NAME, "input.xml", " ''"), BXDB_PATH_X);
    error(_DB_RENAME.args(NAME, "input.xml", " '/'"), BXDB_PATH_X);
    error(_DB_RENAME.args(NAME, "input.xml", " '.'"), BXDB_PATH_X);

    // rename paths
    query(_DB_RENAME.args(NAME, "", "x"));
    query("count(" + COLLECTION.args(NAME + "/x/newtest") + ")", XMLFILES);

    // rename binary file
    query(_DB_STORE.args(NAME, "file1", ""));
    query(_DB_RENAME.args(NAME, "file1", "file2"));
    query(_DB_RETRIEVE.args(NAME, "file2"));
    error(_DB_RETRIEVE.args(NAME, "file1"), WHICHRES_X);

    query(_DB_RENAME.args(NAME, "file2", "dir1/file3"));
    query(_DB_RETRIEVE.args(NAME, "dir1/file3"));
    query(_DB_RENAME.args(NAME, "dir1", "dir2"));
    query(_DB_RETRIEVE.args(NAME, "dir2/file3"));
    error(_DB_RETRIEVE.args(NAME, "dir1"), WHICHRES_X);

    query(_DB_STORE.args(NAME, "file4", ""));
    query(_DB_STORE.args(NAME, "dir3/file5", ""));

    error(_DB_RENAME.args(NAME, "dir2", "file4"), BXDB_PATH_X);
    error(_DB_RENAME.args(NAME, "file4", "dir2"), BXDB_PATH_X);

    // move files in directories
    query(_DB_RENAME.args(NAME, "dir2", "dir3"));
    query(_DB_RETRIEVE.args(NAME, "dir3/file3"));
    query(_DB_RETRIEVE.args(NAME, "dir3/file5"));
  }