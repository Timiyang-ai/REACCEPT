public int lsr(TachyonURI path) throws IOException {
    TachyonFS tachyonClient = createFS(path);
    List<FileInfo> files = tachyonClient.listStatus(path);
    Collections.sort(files);
    String format = "%-10s%-25s%-15s%-5s%n";
    for (FileInfo file : files) {
      String inMemory = "";
      if (!file.isFolder) {
        if (100 == file.inMemoryPercentage) {
          inMemory = "In Memory";
        } else {
          inMemory = "Not In Memory";
        }
      }
      System.out.format(format, FormatUtils.getSizeFromBytes(file.getLength()),
          convertMsToDate(file.getCreationTimeMs()), inMemory, file.getPath());
      if (file.isFolder) {
        lsr(new TachyonURI(path.getScheme(), path.getAuthority(), file.getPath()));
      }
    }
    return 0;
  }