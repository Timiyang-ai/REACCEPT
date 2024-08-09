  @Test
  public void mergeRequest_addNewDownloadAsCompleted() throws IOException {
    StreamKey streamKey1 =
        new StreamKey(/* periodIndex= */ 3, /* groupIndex= */ 4, /* trackIndex= */ 5);
    StreamKey streamKey2 =
        new StreamKey(/* periodIndex= */ 0, /* groupIndex= */ 1, /* trackIndex= */ 2);
    DownloadRequest request1 =
        new DownloadRequest(
            "id1",
            TYPE_PROGRESSIVE,
            Uri.parse("https://www.test.com/download1"),
            asList(streamKey1),
            /* customCacheKey= */ "key123",
            new byte[] {1, 2, 3, 4});
    DownloadRequest request2 =
        new DownloadRequest(
            "id2",
            TYPE_PROGRESSIVE,
            Uri.parse("https://www.test.com/download2"),
            asList(streamKey2),
            /* customCacheKey= */ "key123",
            new byte[] {5, 4, 3, 2, 1});
    ActionFileUpgradeUtil.mergeRequest(
        request1, downloadIndex, /* addNewDownloadAsCompleted= */ false, NOW_MS);

    // Merging existing download, keeps it queued.
    ActionFileUpgradeUtil.mergeRequest(
        request1, downloadIndex, /* addNewDownloadAsCompleted= */ true, NOW_MS);
    assertThat(downloadIndex.getDownload(request1.id).state).isEqualTo(Download.STATE_QUEUED);

    // New download is merged as completed.
    ActionFileUpgradeUtil.mergeRequest(
        request2, downloadIndex, /* addNewDownloadAsCompleted= */ true, NOW_MS);
    assertThat(downloadIndex.getDownload(request2.id).state).isEqualTo(Download.STATE_COMPLETED);
  }