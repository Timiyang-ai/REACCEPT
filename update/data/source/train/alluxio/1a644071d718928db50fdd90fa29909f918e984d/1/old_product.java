private void ls(AlluxioURI path, boolean recursive, boolean forceLoadMetadata, boolean dirAsFile,
                  boolean rawSize, boolean pinnedOnly)
      throws AlluxioException, IOException {
    if (dirAsFile) {
      URIStatus status = mFileSystem.getStatus(path);
      if (pinnedOnly && !status.isPinned()) {
        return;
      }
      printLsString(status, rawSize);
      return;
    }

    ListStatusOptions options = ListStatusOptions.defaults();
    if (forceLoadMetadata) {
      options.setLoadMetadataType(LoadMetadataType.Always);
    }
    List<URIStatus> statuses = listStatusSortedByIncreasingCreationTime(path, options);
    for (URIStatus status : statuses) {
      if (!pinnedOnly || status.isPinned()) {
        printLsString(status, rawSize);
      }
      if (recursive && status.isFolder()) {
        ls(new AlluxioURI(path.getScheme(), path.getAuthority(), status.getPath()), true,
            forceLoadMetadata, false, rawSize, pinnedOnly);
      }
    }
  }