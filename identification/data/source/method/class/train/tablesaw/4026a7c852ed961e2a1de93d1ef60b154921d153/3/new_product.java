@Deprecated
  public void stepWithRows(Consumer<Row[]> rowConsumer, int n) {
    steppingStream(n).forEach(rowConsumer);
  }