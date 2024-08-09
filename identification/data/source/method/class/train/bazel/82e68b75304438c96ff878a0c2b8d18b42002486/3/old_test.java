  protected void createSymbolicLink(Path link, Path target) throws IOException {
    createSymbolicLink(link, target.asFragment());
  }