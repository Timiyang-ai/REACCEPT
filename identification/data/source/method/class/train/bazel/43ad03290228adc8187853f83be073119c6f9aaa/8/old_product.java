public Fingerprint addBoolean(boolean input) {
    try {
      codedOut.writeBoolNoTag(input);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
    return this;
  }