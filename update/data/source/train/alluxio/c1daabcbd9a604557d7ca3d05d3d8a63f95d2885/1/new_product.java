public static boolean createStorageDirPath(String path, String workerDataFolderPermissions)
      throws IOException {
    if (Files.exists(Paths.get(path))) {
      return false;
    }
    Path storagePath;
    try {
      storagePath = Files.createDirectories(Paths.get(path));
    } catch (UnsupportedOperationException | SecurityException | IOException e) {
      throw new IOException("Failed to create folder " + path, e);
    }
    String absolutePath = storagePath.toAbsolutePath().toString();
    changeLocalFilePermission(absolutePath, workerDataFolderPermissions);
    setLocalDirStickyBit(absolutePath);
    return true;
  }