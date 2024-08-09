  @Test
  public void matchPlatform() {

    assertTrue(ExtraDdlXmlReader.matchPlatform("h2", "h2"));
    assertTrue(ExtraDdlXmlReader.matchPlatform("h2", "mysql,h2"));
    assertTrue(ExtraDdlXmlReader.matchPlatform("h2", "mysql,h2,"));
    assertTrue(ExtraDdlXmlReader.matchPlatform("h2", "mysql , h2 ,"));
    assertTrue(ExtraDdlXmlReader.matchPlatform("h2", "mysql , h2, oracle"));
    assertTrue(ExtraDdlXmlReader.matchPlatform("h2", "mysql , h2, oracle"));

    assertTrue(ExtraDdlXmlReader.matchPlatform("sqlserver17", "sqlserver17"));
    assertTrue(ExtraDdlXmlReader.matchPlatform("sqlserver16", "sqlserver16"));
  }