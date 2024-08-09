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
        for (DownloadAction action : actionFile.load()) {
          if (downloadIdProvider != null) {
            action = action.copyWithId(downloadIdProvider.getId(action));
          }
          mergeAction(action, downloadIndex);
        }
        success = true;
      } finally {
        if (success || deleteOnFailure) {
          actionFile.delete();
        }
      }
    }
  }