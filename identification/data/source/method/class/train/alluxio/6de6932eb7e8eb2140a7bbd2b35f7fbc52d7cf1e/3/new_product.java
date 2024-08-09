public static void createDir(String path) throws IOException {
    try {
      Files.createDirectories(Paths.get(path));
    } catch (AccessDeniedException e) {
      throw new IOException("Fail to create folder " + path);
    }
  }