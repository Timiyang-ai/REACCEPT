public int pin(TachyonURI path) {
    try {
      TachyonFile fd = mTfs.open(path);
      SetStateOptions options = new SetStateOptions.Builder(mTachyonConf).setPinned(true).build();
      mTfs.setState(fd, options);
      System.out.println("File '" + path + "' was successfully pinned.");
      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("File '" + path + "' could not be pinned.");
      return -1;
    }
  }