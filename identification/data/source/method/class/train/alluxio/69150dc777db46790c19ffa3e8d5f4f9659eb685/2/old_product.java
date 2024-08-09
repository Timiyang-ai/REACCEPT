public static void createFile(String filePath) throws IOException {
    try {
      Files.createDirectories(Paths.get(filePath));
    } catch (FileAlreadyExistsException e1) {
      throw new IOException("File already exist " + filePath);
    } catch (UnsupportedOperationException e2) {
      throw new IOException("Failed to create file " + filePath);
    } catch (SecurityException e3) {
      throw new IOException("Failed to create file " + filePath);
    } catch (IOException e4) {
      throw new IOException("Failed to create file " + filePath);
    }
  }