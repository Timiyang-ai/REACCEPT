private Str dirName(final QueryContext ctx) throws QueryException {
    final File file = checkFile(0, ctx);
    String par = file.getParent();
    if(par == null) par = file.toString().contains(File.separator) ? "" : ".";
    return Str.get(dir(par));
  }