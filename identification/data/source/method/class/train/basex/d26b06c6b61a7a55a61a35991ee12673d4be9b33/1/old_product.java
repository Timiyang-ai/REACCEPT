private boolean restore(final IOFile file, final String db) {
    try {
      progress(new Zip(file)).unzip(mprop.dbpath());
      context.getDatabases().add(db);
      return true;
    } catch(final IOException ex) {
      Util.debug(ex);
      return false;
    }
  }