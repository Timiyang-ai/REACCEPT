public static void createFile(String filePath) throws IOException {
    File file = new File(filePath);
    Files.createParentDirs(file);
    if (!file.createNewFile()) {
      throw new IOException("File already exists " + filePath);
    }
  }