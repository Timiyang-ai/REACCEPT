public static void createDir(String path) throws IOException {
    try {
      Files.createDirectories(Paths.get(path));
    } catch (AccessDeniedException e) {
      LOG.info("Fail to create folder " + path + "caused by AccessDeniedException");
    }
  }