  @Test
  public void lastVersion() {
    File d = new File("src/test/resources/dbmigration/migrationtest/h2");
    assertThat(LastMigration.lastVersion(d, null)).isEqualTo("1.4");
    assertThat(LastMigration.nextVersion(d, null, false)).isEqualTo("1.5");
    assertThat(LastMigration.nextVersion(d, null, true)).isEqualTo("1.4");
  }