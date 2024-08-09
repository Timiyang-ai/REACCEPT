private synchronized Item createDir(final QueryContext ctx) throws QueryException {
    // resolve symbolic links
    final File path = checkFile(0, ctx);
    File f;
    try {
      f = path.getCanonicalFile();
    } catch(final IOException ex) {
      throw FILE_IE_PATH.get(info, path);
    }

    // find lowest existing path
    while(!f.exists()) {
      f = f.getParentFile();
      if(f == null) throw FILE_IE_PATH.get(info, path);
    }
    // warn if lowest path points to a file
    if(f.isFile()) throw FILE_E.get(info, path);

    // only create directories if path does not exist yet
    if(!path.exists() && !path.mkdirs()) throw FILE_IE_DIR.get(info, path);
    return null;
  }