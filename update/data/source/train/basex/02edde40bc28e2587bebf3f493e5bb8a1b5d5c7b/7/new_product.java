public static synchronized Data create(final String name, final Parser parser,
      final Context ctx) throws IOException {

    // check permissions
    if(!ctx.user.has(Perm.CREATE)) throw new BaseXException(PERM_REQUIRED_X, Perm.CREATE);

    // create main memory database instance
    final MainOptions opts = ctx.options;
    if(opts.is(MainOptions.MAINMEM)) return MemBuilder.build(name, parser);

    // database is currently locked by another process
    if(ctx.pinned(name)) throw new BaseXException(DB_PINNED_X, name);

    // create disk builder, set database path
    final DiskBuilder builder = new DiskBuilder(name, parser, ctx);

    // build database and index structures
    try {
      final Data data = builder.build();
      if(data.meta.createtext) data.setIndex(IndexType.TEXT,
        new ValueIndexBuilder(data, true).build());
      if(data.meta.createattr) data.setIndex(IndexType.ATTRIBUTE,
        new ValueIndexBuilder(data, false).build());
      if(data.meta.createftxt) data.setIndex(IndexType.FULLTEXT,
        new FTBuilder(data).build());
      data.close();
    } finally {
      builder.close();
    }
    return Open.open(name, ctx);
  }