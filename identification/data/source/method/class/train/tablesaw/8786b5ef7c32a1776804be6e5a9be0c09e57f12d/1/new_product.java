@VisibleForTesting
  static Charset getCharSet(File file) {
    long bufferSize = file.length() < 9999 ? file.length() : 9999;
    byte[] buffer = new byte[(int) bufferSize];
    try (InputStream initialStream = new FileInputStream(file)) {
      initialStream.read(buffer);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return getCharSet(buffer);
  }