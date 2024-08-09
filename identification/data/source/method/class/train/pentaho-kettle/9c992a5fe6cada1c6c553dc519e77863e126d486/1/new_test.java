  @Test
  public void testfindDatabase() throws KettleDatabaseException {
    List<DatabaseMeta> databases = new ArrayList<DatabaseMeta>();
    databases.add( new DatabaseMeta( "  1", "Infobright", "JDBC", null, "stub:stub", null, null, null ) );
    databases.add( new DatabaseMeta( "  1  ", "Infobright", "JDBC", null, "stub:stub", null, null, null ) );
    databases.add( new DatabaseMeta( "1  ", "Infobright", "JDBC", null, "stub:stub", null, null, null ) );
    Assert.assertNotNull( DatabaseMeta.findDatabase( databases, "1" ) );
    Assert.assertNotNull( DatabaseMeta.findDatabase( databases, "1 " ) );
    Assert.assertNotNull( DatabaseMeta.findDatabase( databases, " 1" ) );
    Assert.assertNotNull( DatabaseMeta.findDatabase( databases, " 1 " ) );
  }