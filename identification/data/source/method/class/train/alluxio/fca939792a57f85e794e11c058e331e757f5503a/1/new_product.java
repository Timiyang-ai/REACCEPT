public void pin(TachyonURI path) throws IOException {
    try {
      TachyonFile fd = mTfs.open(path);
      SetStateOptions options = new SetStateOptions.Builder().setPinned(true).build();
      mTfs.setState(fd, options);
      System.out.println("File '" + path + "' was successfully pinned.");
    } catch (TachyonException e) {
      throw new IOException(e.getMessage());
    }
  }