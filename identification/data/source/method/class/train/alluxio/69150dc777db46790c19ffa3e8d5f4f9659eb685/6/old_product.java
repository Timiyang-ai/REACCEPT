public static void createFile(String filePath) throws IOException {
    Path storagePath = Paths.get(filePath);
    Files.createDirectories(storagePath.getParent());
    try {
      Files.createFile(storagePath);
    } catch (FileAlreadyExistsException e) {
      throw new IOException("File already exist " + filePath);
    }
  }