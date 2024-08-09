private Uri pathToUri(final QueryContext ctx) throws QueryException {
    return Uri.uri(checkPath(0, ctx).toUri().toString());
  }