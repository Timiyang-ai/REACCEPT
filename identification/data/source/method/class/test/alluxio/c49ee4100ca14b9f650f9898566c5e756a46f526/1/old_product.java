public static void createBlockPath(String path) throws IOException {
    try {
      createStorageDirPath(PathUtils.getParent(path));
    } catch (InvalidPathException e) {
      throw new IOException("Failed to create block path, get parent path of " + path + "failed");
    } catch (IOException ioe) {
      throw new IOException("Failed to create block path " + path);
    }
  }