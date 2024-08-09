private Iter search(final QueryContext ctx) throws QueryException {
    final Data data = checkData(ctx);
    final Value terms = ctx.value(expr[1]);
    final FTOptions opts = checkOptions(2, Q_OPTIONS, new FTOptions(), ctx);

    final IndexContext ic = new IndexContext(data, false);
    if(!data.meta.ftxtindex) BXDB_INDEX.thrw(info, data.meta.name,
        IndexType.FULLTEXT.toString().toLowerCase(Locale.ENGLISH));

    final FTOpt tmp = ctx.ftOpt();
    final FTOpt opt = new FTOpt().copy(data.meta);
    FTMode mode = FTMode.ANY;
    if(opts != null) {
      opt.set(FZ, opts.get(FTOptions.FUZZY));
      opt.set(WC, opts.get(FTOptions.WILDCARDS));
      mode = opts.get(FTOptions.MODE);
    }
    ctx.ftOpt(opt);
    FTExpr fte = new FTWords(info, ic, terms, mode, ctx);
    ctx.ftOpt(tmp);

    if(opts != null) {
      if(opts.get(FTOptions.ORDERED)) {
        fte = new FTOrder(info, fte);
      }
      if(opts.contains(FTOptions.DISTANCE)) {
        final FTDistanceOptions fopts = opts.get(FTOptions.DISTANCE);
        final Int min = Int.get(fopts.get(FTDistanceOptions.MIN));
        final Int max = Int.get(fopts.get(FTDistanceOptions.MAX));
        final FTUnit unit = fopts.get(FTDistanceOptions.UNIT);
        fte = new FTDistance(info, fte, min, max, unit);
      }
      if(opts.contains(FTOptions.WINDOW)) {
        final FTWindowOptions fopts = opts.get(FTOptions.WINDOW);
        final Int sz = Int.get(fopts.get(FTWindowOptions.SIZE));
        final FTUnit unit = fopts.get(FTWindowOptions.UNIT);
        fte = new FTWindow(info, fte, sz, unit);
      }
      if(opts.contains(FTOptions.SCOPE)) {
        final FTScopeOptions fopts = opts.get(FTOptions.SCOPE);
        final boolean same = fopts.get(FTScopeOptions.SAME);
        final FTUnit unit = fopts.get(FTScopeOptions.UNIT).unit();
        fte = new FTScope(info, fte, same, unit);
      }
      if(opts.contains(FTOptions.CONTENT)) {
        final FTContents cont = opts.get(FTOptions.CONTENT);
        fte = new FTContent(info, fte, cont);
      }
    }
    return new FTIndexAccess(info, fte, ic).iter(ctx);
  }