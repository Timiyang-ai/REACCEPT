public static void createDir(String path) throws IOException {
    Files.createDirectories(Paths.get(path));
  }