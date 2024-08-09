  @Test
  public void removeVersion_removesSetVersion() throws DatabaseIOException {
    VersionTable.setVersion(database, FEATURE_1, INSTANCE_1, 1);
    VersionTable.setVersion(database, FEATURE_1, INSTANCE_2, 2);
    assertThat(VersionTable.getVersion(database, FEATURE_1, INSTANCE_1)).isEqualTo(1);
    assertThat(VersionTable.getVersion(database, FEATURE_1, INSTANCE_2)).isEqualTo(2);

    VersionTable.removeVersion(database, FEATURE_1, INSTANCE_1);
    assertThat(VersionTable.getVersion(database, FEATURE_1, INSTANCE_1))
        .isEqualTo(VersionTable.VERSION_UNSET);
    assertThat(VersionTable.getVersion(database, FEATURE_1, INSTANCE_2)).isEqualTo(2);
  }