public static void createStorageDirPath(String path) throws IOException {
    File dir = new File(path);
    String absolutePath = dir.getAbsolutePath();
    if (dir.exists()) {
      return;
    }
    if (!dir.mkdirs()) {
      if (dir.exists()) {
        // This dir has been created concurrently.
        return;
      }
      throw new IOException("Failed to create folder " + path);
    }
    changeLocalFileToFullPermission(absolutePath);
    setLocalDirStickyBit(absolutePath);
    LOG.info("Folder {} was created!", path);
  }