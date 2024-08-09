  private boolean put(String execPath, int value) {
    return map.putWithNoDepOwner(new TestInput(execPath), new TestMetadata(value));
  }