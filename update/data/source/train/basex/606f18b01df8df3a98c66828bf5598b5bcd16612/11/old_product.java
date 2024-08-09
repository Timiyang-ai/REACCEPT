private Uri pathToUri(final QueryContext ctx) throws QueryException {
    return Uri.uri(checkFile(0, ctx).toURI().toString());
  }