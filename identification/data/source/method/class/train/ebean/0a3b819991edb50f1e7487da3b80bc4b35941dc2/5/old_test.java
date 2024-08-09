  @Test
  public void convertQuotedIdentifiers() throws Exception {

    ServerConfig config = new ServerConfig();

    DatabasePlatform dbPlatform = new SqlServer17Platform();
    dbPlatform.configure(config.getPlatformConfig(), config.isAllQuotedIdentifiers());

    assertEquals(dbPlatform.convertQuotedIdentifiers("order"),"order");
    assertEquals(dbPlatform.convertQuotedIdentifiers("`order`"),"[order]");
    assertEquals(dbPlatform.convertQuotedIdentifiers("firstName"),"firstName");

    assertEquals(dbPlatform.unQuote("order"),"order");
    assertEquals(dbPlatform.unQuote("[order]"),"order");
    assertEquals(dbPlatform.unQuote("[firstName]"),"firstName");
  }