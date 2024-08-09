  @Test
  public void databaseType() {
    assertEquals("h2", HikariModule.databaseType("jdbc:h2:mem:123;DB_CLOSE_DELAY=-1"));
    assertEquals("h2", HikariModule.databaseType("jdbc:h2:target/jdbctest"));
    assertEquals("mysql", HikariModule.databaseType("jdbc:mysql://localhost/db"));
    assertEquals("derby", HikariModule.databaseType("jdbc:derby:testdb"));
    assertEquals("db2", HikariModule.databaseType("jdbc:db2://127.0.0.1:50000/SAMPLE"));
    assertEquals("hsqldb", HikariModule.databaseType("jdbc:hsqldb:file"));
    assertEquals("mariadb", HikariModule.databaseType("jdbc:mariadb://localhost/dba"));
    assertEquals("log4jdbc", HikariModule.databaseType("jdbc:log4jdbc:mysql://localhost/dbb"));
    assertEquals("mysql",
        HikariModule.databaseType("jdbc:mysql://localhost/dbc?useEncoding=true&characterEncoding=UTF-8"));
    assertEquals("sqlserver", HikariModule.databaseType(
        "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks;integratedSecurity=true;"));
    assertEquals("sqlserver", HikariModule.databaseType(
        "jdbc:sqlserver://localhost:1433;database=AdventureWorks;integratedSecurity=true;"));
    assertEquals("sqlserver",
        HikariModule.databaseType("jdbc:sqlserver://localhost:1433;fpp;databaseName=AdventureWorks;;"));
    assertEquals("oracle", HikariModule.databaseType("jdbc:oracle:thin:@myhost:1521:orcl"));
    assertEquals("pgsql", HikariModule.databaseType("jdbc:pgsql://server/database"));
    assertEquals("postgresql", HikariModule.databaseType("jdbc:postgresql://server/database"));
    assertEquals("sybase", HikariModule.databaseType("jdbc:jtds:sybase://server/database"));
    assertEquals("firebirdsql", HikariModule.databaseType("jdbc:firebirdsql:host:mydb"));
    assertEquals("sqlite", HikariModule.databaseType("jdbc:sqlite:testdb"));
    assertEquals("unknown", HikariModule.databaseType("jdbc:unknown:testdb"));
    assertEquals("foo", HikariModule.databaseType("foo"));
  }