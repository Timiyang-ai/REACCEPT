public QNm parseLibrary(final String qu, final String path) throws QueryException {
    return new QueryParser(qu, path, this).parseLibrary(true);
  }