private static String add(final Parser parser, final Context ctx,
      final String target, final String name, final Add cmd)
      throws BaseXException {

    final Performance p = new Performance();
    final String path = target + (target.isEmpty() ? "/" : "") +
        (name == null ? parser.src.name() : name);

    // create disk instances for large documents
    // test does not work for input streams and directories
    final long fl = parser.src.length();
    boolean large = false;
    final Runtime rt = Runtime.getRuntime();
    if(fl > rt.freeMemory() / 3) {
      Performance.gc(2);
      large = fl > rt.freeMemory() / 3;
    }

    // create random database name
    final Data data = ctx.data;
    final String dbname = large ? ctx.mprop.random(data.meta.name) : path;
    final Builder build = large ? new DiskBuilder(dbname, parser, ctx) :
      new MemBuilder(dbname, parser, ctx.prop);
    if(cmd != null) cmd.build = build;

    Data tmp = null;
    try {
      tmp = build.build();
      // ignore empty fragments
      if(tmp.meta.size > 1) {
        data.insert(data.meta.size, -1, tmp);
        ctx.update();
        data.flush();
      }
      return Util.info(PATHADDED, path, p);
    } catch(final IOException ex) {
      Util.debug(ex);
      throw new BaseXException(ex);
    } finally {
      // close and drop intermediary database instance
      if(tmp != null) try { tmp.close(); } catch(final IOException e) { }
      if(large) DropDB.drop(dbname, ctx.mprop);
    }
  }