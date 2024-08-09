static File getResourceAsTempFile(String resourceName) throws IOException {
    checkArgument(!Strings.isNullOrEmpty(resourceName), "resourceName");

    File file = File.createTempFile(resourceName, ".tmp");
    OutputStream os = new FileOutputStream(file);
    try {
      getResourceAsTempFile(resourceName, file, os);
      return file;
    } finally {
      os.close();
    }
  }