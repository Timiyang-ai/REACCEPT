static Iter search(final Data data, final Value terms, final FTOptions opts,
      final StandardFunc fun, final QueryContext ctx) throws QueryException {

    final InputInfo info = fun.info;
    final IndexContext ic = new IndexContext(data, false);
    if(!data.meta.ftxtindex) BXDB_INDEX.thrw(info, data.meta.name,
        IndexType.FULLTEXT.toString().toLowerCase(Locale.ENGLISH));

    final FTOpt tmp = ctx.ftOpt();
    final FTOpt opt = new FTOpt().copy(data.meta);
    FTMode mode = FTMode.ANY;
    if(opts != null) {
      opt.set(FZ, opts.get(FTOptions.FUZZY));
      opt.set(WC, opts.get(FTOptions.WILDCARDS));
      final String md = opts.get(FTOptions.MODE);
      mode = FTMode.get(md);
      if(mode == null) INVALIDOPT.thrw(info, Util.info(Text.INVALID_VALUE_X_X, "mode", md));
    }

    ctx.ftOpt(opt);
    final FTWords words = new FTWords(info, ic, terms, mode, ctx);
    ctx.ftOpt(tmp);
    return new FTIndexAccess(info, words, ic).iter(ctx);
  }