  @Test
  public void putDownload_setsVersion() throws DatabaseIOException {
    SQLiteDatabase readableDatabase = databaseProvider.getReadableDatabase();
    assertThat(VersionTable.getVersion(readableDatabase, VersionTable.FEATURE_OFFLINE, EMPTY_NAME))
        .isEqualTo(VersionTable.VERSION_UNSET);

    downloadIndex.putDownload(new DownloadBuilder("id1").build());

    assertThat(VersionTable.getVersion(readableDatabase, VersionTable.FEATURE_OFFLINE, EMPTY_NAME))
        .isEqualTo(DefaultDownloadIndex.TABLE_VERSION);
  }