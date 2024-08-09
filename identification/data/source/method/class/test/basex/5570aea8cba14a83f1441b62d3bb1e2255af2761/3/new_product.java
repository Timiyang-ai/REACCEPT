public static synchronized Data create(final IO source, final boolean single,
      final Context ctx) throws IOException {

    // check if input is an existing file
    if(!source.exists() || single && source.isDir())
      throw new BaseXException(RES_NOT_FOUND_X, source);

    // default: create a main memory instance
    if(!ctx.options.get(MainOptions.FORCECREATE)) return CreateDB.mainMem(source, ctx);

    // otherwise, create a persistent database instance
    final String nm = source.dbname();
    final DirParser dp = new DirParser(source, ctx.options, ctx.globalopts.dbpath(nm));
    return CreateDB.create(nm, dp, ctx);
  }