public List<String> getLines() {
    try {
      return CharSource.wrap(sourceBuilder).readLines();
    } catch (IOException e) {
      throw new AssertionError("IOException not possible, as the string is in-memory", e);
    }
  }