  @Test
  public void getCurrentDownloads_returnsCurrentDownloads() {
    TaskWrapper task1 = new DownloadRunner(uri1).postDownloadRequest().getTask();
    TaskWrapper task2 = new DownloadRunner(uri2).postDownloadRequest().getTask();
    TaskWrapper task3 =
        new DownloadRunner(uri3).postDownloadRequest().postRemoveRequest().getTask();

    task3.assertRemoving();
    List<Download> downloads = downloadManager.getCurrentDownloads();

    assertThat(downloads).hasSize(3);
    String[] taskIds = {task1.taskId, task2.taskId, task3.taskId};
    String[] downloadIds = {
      downloads.get(0).request.id, downloads.get(1).request.id, downloads.get(2).request.id
    };
    assertThat(downloadIds).isEqualTo(taskIds);
  }