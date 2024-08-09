public static void createFile(String filePath) throws IOException {
    Path storagePath = Paths.get(filePath);
    Files.createDirectories(storagePath.getParent());
    Files.createFile(storagePath);
  }