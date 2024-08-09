static void mergeRequest(
      DownloadRequest request, DefaultDownloadIndex downloadIndex) throws IOException {
    Download download = downloadIndex.getDownload(request.id);
    if (download != null) {
      download = DownloadManager.mergeRequest(download, request, download.manualStopReason);
    } else {
      long nowMs = System.currentTimeMillis();
      download =
          new Download(
              request,
              STATE_QUEUED,
              Download.FAILURE_REASON_NONE,
              Download.MANUAL_STOP_REASON_NONE,
              /* startTimeMs= */ nowMs,
              /* updateTimeMs= */ nowMs);
    }
    downloadIndex.putDownload(download);
  }