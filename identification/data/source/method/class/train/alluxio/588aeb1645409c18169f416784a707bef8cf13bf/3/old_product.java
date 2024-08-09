public static void createStorageDirPath(String path) throws IOException {
    File dir = new File(path);
    String absolutePath = dir.getAbsolutePath();
    if (!dir.exists()) {
      if (dir.mkdirs()) {
        changeLocalFileToFullPermission(absolutePath);
        setLocalDirStickyBit(absolutePath);
        LOG.info("Folder {} was created!", path);
      } else {
        throw new IOException("Failed to create folder " + path);
      }
    }
  }