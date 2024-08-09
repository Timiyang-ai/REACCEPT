public QNm module(final String qu, final String path) throws QueryException {
    return new QueryParser(qu, path, this).parseModule(true);
  }