private Str pathToNative(final QueryContext qc) throws QueryException, IOException {
    final Path nat = toPath(0, qc).toRealPath();
    return get(nat, Files.isDirectory(nat));
  }