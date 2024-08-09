  @Test
  public void configure_customType() throws Exception {

    PlatformConfig config = new PlatformConfig();
    config.addCustomMapping(DbType.VARCHAR, "text", Platform.POSTGRES);
    config.addCustomMapping(DbType.DECIMAL, "decimal(24,4)");

    // PG renders custom decimal and varchar
    PostgresPlatform pgPlatform = new PostgresPlatform();
    pgPlatform.configure(config, false);
    assertEquals(defaultDecimalDefn(pgPlatform), "decimal(24,4)");
    assertEquals(defaultDefn(DbType.VARCHAR, pgPlatform), "text");

    // H2 only renders custom decimal
    H2Platform h2Platform = new H2Platform();
    h2Platform.configure(config, false);
    assertEquals(defaultDecimalDefn(h2Platform), "decimal(24,4)");
    assertEquals(defaultDefn(DbType.VARCHAR, h2Platform), "varchar(255)");
  }