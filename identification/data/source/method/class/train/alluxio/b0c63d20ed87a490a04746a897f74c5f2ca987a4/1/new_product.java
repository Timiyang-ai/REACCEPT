public int pin(TachyonURI path) {
    try {
      TachyonFile fd = mTfs.open(path);
      mTfs.setPin(fd, true);
      System.out.println("File '" + path + "' was successfully pinned.");
      return 0;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("File '" + path + "' could not be pinned.");
      return -1;
    }
  }