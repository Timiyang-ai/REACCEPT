private StrStream readText(final QueryContext ctx) throws QueryException {
    final File path = checkFile(0, ctx);
    final String enc = encoding(1, FILE_UE, ctx);
    if(!path.exists()) throw FILE_NF.get(info, path.getAbsolutePath());
    if(path.isDirectory()) throw FILE_ID.get(info, path.getAbsolutePath());
    return new StrStream(new IOFile(path), enc, FILE_IE, ctx);
  }