  @Test
  public void databaseName() {
    assertEquals("123", HikariModule.databaseName("jdbc:h2:mem:123;DB_CLOSE_DELAY=-1"));
    assertEquals("jdbctest", HikariModule.databaseName("jdbc:h2:target/jdbctest"));
    assertEquals("db", HikariModule.databaseName("jdbc:mysql://localhost/db"));
    assertEquals("testdb", HikariModule.databaseName("jdbc:derby:testdb"));
    assertEquals("SAMPLE", HikariModule.databaseName("jdbc:db2://127.0.0.1:50000/SAMPLE"));
    assertEquals("file", HikariModule.databaseName("jdbc:hsqldb:file"));
    assertEquals("dba", HikariModule.databaseName("jdbc:mariadb://localhost/dba"));
    assertEquals("dbb", HikariModule.databaseName("jdbc:log4jdbc:mysql://localhost/dbb"));
    assertEquals("dbc",
        HikariModule.databaseName("jdbc:mysql://localhost/dbc?useEncoding=true&characterEncoding=UTF-8"));
    assertEquals("AdventureWorks", HikariModule.databaseName(
        "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks;integratedSecurity=true;"));
    assertEquals("AdventureWorks", HikariModule.databaseName(
        "jdbc:sqlserver://localhost:1433;database=AdventureWorks;integratedSecurity=true;"));
    assertEquals("AdventureWorks",
        HikariModule.databaseName("jdbc:sqlserver://localhost:1433;fpp;databaseName=AdventureWorks;;"));
    assertEquals("orcl", HikariModule.databaseName("jdbc:oracle:thin:@myhost:1521:orcl"));
    assertEquals("database", HikariModule.databaseName("jdbc:pgsql://server/database"));
    assertEquals("database", HikariModule.databaseName("jdbc:postgresql://server/database"));
    assertEquals("database", HikariModule.databaseName("jdbc:jtds:sybase://server/database"));
    assertEquals("mydb", HikariModule.databaseName("jdbc:firebirdsql:host:mydb"));
    assertEquals("testdb", HikariModule.databaseName("jdbc:sqlite:testdb"));
    assertEquals("testdb", HikariModule.databaseName("jdbc:unknown:testdb"));
  }