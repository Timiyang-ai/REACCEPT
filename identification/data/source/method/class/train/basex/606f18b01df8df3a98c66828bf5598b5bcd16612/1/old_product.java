private Str pathToNative(final QueryContext ctx) throws QueryException {
    final File path = checkFile(0, ctx);
    try {
      final String nat = path.getCanonicalFile().getPath();
      return Str.get(path.isDirectory() ? dir(nat) : nat);
    } catch(final IOException ex) {
      throw FILE_IE_PATH.get(info, path);
    }
  }