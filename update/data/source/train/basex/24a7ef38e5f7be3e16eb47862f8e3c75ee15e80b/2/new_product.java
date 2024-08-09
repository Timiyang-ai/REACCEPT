private Iter list(final QueryContext ctx) throws QueryException {
    final String path = string(checkStr(expr[0], ctx));
    final File dir = new File(path);

    // Check if not a directory
    if(!dir.isDirectory()) NOTDIR.thrw(input, path);

    final boolean rec = optionalBool(1, ctx);
    final String pat = expr.length != 3 ? null :
      IOFile.regex(string(checkStr(expr[2], ctx)));

    File[] fl;
    if(rec) {
      final List<File> list = new ArrayList<File>();
      recList(dir, list);
      fl = list.toArray(new File[list.size()]);
    } else {
      fl = dir.listFiles();
      if(fl == null) CANNOTLIST.thrw(input, path);
    }
    final File[] files = fl;

    return new Iter() {
      int c = -1;

      @Override
      public Item next() {
        while(++c < files.length) {
          final File f = files[c];
          final String n = Prop.WIN ? f.getName().toLowerCase() : f.getName();
          if(pat == null || n.matches(pat)) return Str.get(f.getPath());
        }
        return null;
      }
    };
  }