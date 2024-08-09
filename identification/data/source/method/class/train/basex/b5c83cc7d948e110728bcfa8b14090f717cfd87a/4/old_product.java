private Iter list(final QueryContext ctx) throws QueryException {
    // get canonical representation to resolve symbolic links
    File dir = checkFile(0, ctx);
    try {
      dir = new File(dir.getCanonicalPath());
    } catch(final IOException ex) {
      throw FILE_PATH.get(info, dir);
    }

    // check if the addresses path is a directory
    if(!dir.isDirectory()) throw FILE_NODIR.get(info, dir);

    final boolean rec = optionalBool(1, ctx);
    final Pattern pat = expr.length == 3 ? Pattern.compile(IOFile.regex(
        string(checkStr(expr[2], ctx))), Prop.CASE ? 0 : Pattern.CASE_INSENSITIVE) : null;

    final StringList list = new StringList();
    final String p = dir.getPath();
    final int l = p.length() + (p.endsWith(File.separator) ? 0 : 1);
    list(l, dir, list, rec, pat);

    return new Iter() {
      int c;
      @Override
      public Item next() {
        return c < list.size() ? Str.get(list.get(c++)) : null;
      }
    };
  }