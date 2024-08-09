  @Test
  public void upgradeAndDelete_createsDownloads() throws IOException {
    // Copy the test asset to a file.
    byte[] actionFileBytes =
        TestUtil.getByteArray(
            ApplicationProvider.getApplicationContext(),
            "offline/action_file_for_download_index_upgrade.exi");
    try (FileOutputStream output = new FileOutputStream(tempFile)) {
      output.write(actionFileBytes);
    }

    StreamKey expectedStreamKey1 =
        new StreamKey(/* periodIndex= */ 3, /* groupIndex= */ 4, /* trackIndex= */ 5);
    StreamKey expectedStreamKey2 =
        new StreamKey(/* periodIndex= */ 0, /* groupIndex= */ 1, /* trackIndex= */ 2);
    DownloadRequest expectedRequest1 =
        new DownloadRequest(
            "key123",
            /* type= */ "test",
            Uri.parse("https://www.test.com/download1"),
            asList(expectedStreamKey1),
            /* customCacheKey= */ "key123",
            new byte[] {1, 2, 3, 4});
    DownloadRequest expectedRequest2 =
        new DownloadRequest(
            "key234",
            /* type= */ "test",
            Uri.parse("https://www.test.com/download2"),
            asList(expectedStreamKey2),
            /* customCacheKey= */ "key234",
            new byte[] {5, 4, 3, 2, 1});

    ActionFileUpgradeUtil.upgradeAndDelete(
        tempFile,
        /* downloadIdProvider= */ null,
        downloadIndex,
        /* deleteOnFailure= */ true,
        /* addNewDownloadsAsCompleted= */ false);

    assertDownloadIndexContainsRequest(expectedRequest1, Download.STATE_QUEUED);
    assertDownloadIndexContainsRequest(expectedRequest2, Download.STATE_QUEUED);
  }