private boolean restore(final IOFile file) {
    try {
      progress(new Zip(file)).unzip(mprop.dbpath());
      return true;
    } catch(final IOException ex) {
      Util.debug(ex);
      return false;
    }
  }