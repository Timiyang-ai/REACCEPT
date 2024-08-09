  @Test
  public void getVersion_returnsSetVersion() throws DatabaseIOException {
    VersionTable.setVersion(database, FEATURE_1, INSTANCE_1, 1);
    assertThat(VersionTable.getVersion(database, FEATURE_1, INSTANCE_1)).isEqualTo(1);

    VersionTable.setVersion(database, FEATURE_1, INSTANCE_1, 2);
    assertThat(VersionTable.getVersion(database, FEATURE_1, INSTANCE_1)).isEqualTo(2);

    VersionTable.setVersion(database, FEATURE_2, INSTANCE_1, 3);
    assertThat(VersionTable.getVersion(database, FEATURE_2, INSTANCE_1)).isEqualTo(3);
    assertThat(VersionTable.getVersion(database, FEATURE_1, INSTANCE_1)).isEqualTo(2);

    VersionTable.setVersion(database, FEATURE_2, INSTANCE_2, 4);
    assertThat(VersionTable.getVersion(database, FEATURE_2, INSTANCE_2)).isEqualTo(4);
    assertThat(VersionTable.getVersion(database, FEATURE_2, INSTANCE_1)).isEqualTo(3);
    assertThat(VersionTable.getVersion(database, FEATURE_1, INSTANCE_1)).isEqualTo(2);
  }