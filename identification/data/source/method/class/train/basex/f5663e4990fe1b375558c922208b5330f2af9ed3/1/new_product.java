public boolean delete() {
    boolean ok = true;
    if(isDir()) for(final IOFile ch : children()) ok &= ch.delete();
    // some file systems require several runs
    for(int i = 0; i < 10; i++) {
      if(file.delete() && !file.exists()) return ok;
      Performance.sleep(i * 10);
    }
    return false;
  }