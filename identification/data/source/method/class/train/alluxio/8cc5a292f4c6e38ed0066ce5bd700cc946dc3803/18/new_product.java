private void load(AlluxioURI filePath, int replication)
      throws AlluxioException, IOException, InterruptedException {
    URIStatus status = mFileSystem.getStatus(filePath);
    if (status.isFolder()) {
      List<URIStatus> statuses = mFileSystem.listStatus(filePath);
      for (URIStatus uriStatus : statuses) {
        AlluxioURI newPath = new AlluxioURI(uriStatus.getPath());
        load(newPath, replication);
      }
    } else {
      Thread thread = JobGrpcClientUtils.createProgressThread(System.out);
      thread.start();
      try {
        JobGrpcClientUtils.run(new LoadConfig(filePath.getPath(), replication), 3);
      } finally {
        thread.interrupt();
      }
    }
    System.out.println(filePath + " loaded");
  }