  @Test
  public void test_parse_hyphenSnapshot() {

    MigrationVersion version = MigrationVersion.parse("0.1.1-SNAPSHOT");
    assertThat(version.normalised()).isEqualTo("0.1.1");
    assertThat(version.getComment()).isEqualTo("");
  }