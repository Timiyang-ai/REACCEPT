private Str name(final QueryContext ctx) throws QueryException {
    return Str.get(checkFile(0, ctx).getName());
  }