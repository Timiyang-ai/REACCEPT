public static void createBlockPath(String path, String workerDataFolderPermissions)
      throws IOException {
    try {
      createStorageDirPath(PathUtils.getParent(path), workerDataFolderPermissions);
    } catch (InvalidPathException e) {
      throw new IOException("Failed to create block path, get parent path of " + path + "failed",
          e);
    } catch (IOException e) {
      throw new IOException("Failed to create block path " + path, e);
    }
  }