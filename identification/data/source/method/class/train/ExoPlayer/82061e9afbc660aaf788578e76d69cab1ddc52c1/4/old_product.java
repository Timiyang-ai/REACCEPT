static void mergeRequest(
      DownloadRequest request,
      DefaultDownloadIndex downloadIndex,
      boolean addNewDownloadAsCompleted,
      long nowMs)
      throws IOException {
    Download download = downloadIndex.getDownload(request.id);
    if (download != null) {
      download = DownloadManager.mergeRequest(download, request, download.stopReason, nowMs);
    } else {
      download =
          new Download(
              request,
              addNewDownloadAsCompleted ? Download.STATE_COMPLETED : STATE_QUEUED,
              /* startTimeMs= */ nowMs,
              /* updateTimeMs= */ nowMs,
              /* contentLength= */ C.LENGTH_UNSET,
              Download.STOP_REASON_NONE,
              Download.FAILURE_REASON_NONE);
    }
    downloadIndex.putDownload(download);
  }