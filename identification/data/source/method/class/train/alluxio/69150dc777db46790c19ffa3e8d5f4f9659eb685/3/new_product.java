public static void createFile(String filePath) throws IOException {
    Path storagePath = Paths.get(filePath);
    Path parent = storagePath.getParent();
    if (parent != null) {
      Files.createDirectories(parent);
    }
    Files.createFile(storagePath);
  }