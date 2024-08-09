@Test
  public void attribute() throws BaseXException {
    // run function without and with index
    new DropIndex(Commands.CmdIndex.ATTRIBUTE).execute(context);
    query(DATA.args(_DB_ATTRIBUTE.args(NAME, "0")), "0");
    new CreateIndex(Commands.CmdIndex.ATTRIBUTE).execute(context);
    query(DATA.args(_DB_ATTRIBUTE.args(NAME, "0")), "0");
    query(DATA.args(_DB_ATTRIBUTE.args(NAME, "0", "id")), "0");
    query(DATA.args(_DB_ATTRIBUTE.args(NAME, "0", "XXX")), "");
    query(DATA.args(_DB_ATTRIBUTE.args(NAME, "XXX")), "");
  }