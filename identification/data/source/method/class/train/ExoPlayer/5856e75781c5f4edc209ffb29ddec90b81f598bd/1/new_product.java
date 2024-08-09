@SuppressWarnings("deprecation")
  public static void upgradeAndDelete(
      File actionFilePath,
      @Nullable DownloadIdProvider downloadIdProvider,
      DefaultDownloadIndex downloadIndex,
      boolean deleteOnFailure)
      throws IOException {
    ActionFile actionFile = new ActionFile(actionFilePath);
    if (actionFile.exists()) {
      boolean success = false;
      try {
        for (DownloadRequest request : actionFile.load()) {
          if (downloadIdProvider != null) {
            request = request.copyWithId(downloadIdProvider.getId(request));
          }
          mergeRequest(request, downloadIndex);
        }
        success = true;
      } finally {
        if (success || deleteOnFailure) {
          actionFile.delete();
        }
      }
    }
  }