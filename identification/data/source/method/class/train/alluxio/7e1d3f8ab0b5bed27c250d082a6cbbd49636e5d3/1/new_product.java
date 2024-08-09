public void lsr(TachyonURI path) throws IOException {
    List<FileInfo> files = listStatusSortedByIncreasingCreationTime(path);
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
  }