public int pin(TachyonURI path) throws IOException {
    TachyonFS tachyonClient = createFS(path);
    int fileId = tachyonClient.getFileId(path);

    try {
      tachyonClient.pinFile(fileId);
      System.out.println("File '" + path + "' was successfully pinned.");
      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("File '" + path + "' could not be pinned.");
      return -1;
    }
  }