  private void addOneTimePass(String name) {
    optimizer.addOneTimePass(
        createPassFactory(name, 0, true));
  }