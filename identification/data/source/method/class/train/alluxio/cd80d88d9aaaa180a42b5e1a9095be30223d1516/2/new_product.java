public int rename(String[] argv) throws IOException {
    TachyonURI srcPath = new TachyonURI(argv[1]);
    TachyonURI dstPath = new TachyonURI(argv[2]);
    try {
      if (mTfs.rename(mTfs.open(srcPath), dstPath)) {
        System.out.println("Renamed " + srcPath + " to " + dstPath);
        return 0;
      } else {
        System.out.println("mv: Failed to rename " + srcPath + " to " + dstPath);
        return -1;
      }
    } catch (TachyonException e) {
      throw new IOException(e.getMessage());
    }
  }