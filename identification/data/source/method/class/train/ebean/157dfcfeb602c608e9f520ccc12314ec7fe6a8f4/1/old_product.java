public Autotune read(File file) {

    try {
      return readFile(file);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }