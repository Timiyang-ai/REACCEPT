@Test
  public void text() {
    // run function without and with index
    execute(new DropIndex(CmdIndex.TEXT));
    error(_DB_TEXT.args(NAME, "XML"), BXDB_INDEX_X);
    execute(new CreateIndex(CmdIndex.TEXT));
    query(_DB_TEXT.args(NAME, "XML"), "XML");
    query(_DB_TEXT.args(NAME, "XXX"), "");
  }