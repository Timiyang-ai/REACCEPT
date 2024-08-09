static File getResourceAsTempFile(String resourceName) throws IOException {
    checkArgument(!Strings.isNullOrEmpty(resourceName));

    File file = File.createTempFile(resourceName, ".tmp");
    try (OutputStream os = new FileOutputStream(file)) {
      getResourceAsTempFile(resourceName, file, os);
      return file;
    }
  }