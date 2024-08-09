private void ls(AlluxioURI path, boolean recursive, boolean forceLoadMetadata, boolean dirAsFile,
      boolean hSize, boolean pinnedOnly, String sortField, boolean reverse, String timestampOption)
      throws AlluxioException, IOException {
    URIStatus pathStatus = mFileSystem.getStatus(path);
    if (dirAsFile) {
      if (pinnedOnly && !pathStatus.isPinned()) {
        return;
      }
      printLsString(pathStatus, hSize, timestampOption);
      return;
    }

    ListStatusPOptions.Builder optionsBuilder = ListStatusPOptions.newBuilder();
    if (forceLoadMetadata) {
      optionsBuilder.setLoadMetadataType(LoadMetadataPType.ALWAYS);
    }
    optionsBuilder.setRecursive(recursive);

    // If list status takes too long, print the message
    Timer timer = new Timer();
    if (pathStatus.isFolder()) {
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          System.out.printf("Getting directory status of %s files or sub-directories "
              + "may take a while.%n", pathStatus.getLength());
        }
      }, 10000);
    }
    List<URIStatus> statuses = mFileSystem.listStatus(path, optionsBuilder.build());
    timer.cancel();

    List<URIStatus> sorted = sortByFieldAndOrder(statuses, sortField, reverse);
    for (URIStatus status : sorted) {
      if (!pinnedOnly || status.isPinned()) {
        printLsString(status, hSize, timestampOption);
      }
    }
  }