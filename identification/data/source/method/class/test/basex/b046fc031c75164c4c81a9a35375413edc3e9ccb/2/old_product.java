private Str pathToNative(final QueryContext qc) throws QueryException, IOException {
    final Path nat = checkPath(0, qc).toRealPath();
    return get(nat, Files.isDirectory(nat));
  }