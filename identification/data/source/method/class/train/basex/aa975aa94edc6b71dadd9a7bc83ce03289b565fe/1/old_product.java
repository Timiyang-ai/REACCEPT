static Iter search(final Data data, final Value terms, final TokenMap map,
      final StandardFunc fun, final QueryContext ctx) throws QueryException {

    final InputInfo info = fun.info;
    final IndexContext ic = new IndexContext(ctx, data, null, true);
    if(!data.meta.ftxtindex) BXDB_INDEX.thrw(info, data.meta.name,
        IndexType.FULLTEXT.toString().toLowerCase(Locale.ENGLISH));

    final FTOpt tmp = ctx.ftOpt();
    final FTOpt opt = new FTOpt().copy(data.meta);
    FTMode m = FTMode.ANY;
    if(map != null) {
      for(final byte[] k : map) {
        final byte[] v = map.get(k);
        if(eq(k, FUZZY)) {
          final boolean t = v.length == 0 || Util.yes(string(v));
          opt.set(FZ, t);
        } else if(eq(k, WILDCARDS)) {
          final boolean t = v.length == 0 || Util.yes(string(v));
          opt.set(WC, t);
        } else if(eq(k, MODE)) {
          m = FTMode.get(v);
          if(m == null) ELMOPTION.thrw(info, v);
        } else {
          ELMOPTION.thrw(info, k);
        }
      }
    }

    ctx.ftOpt(opt);
    final FTWords words = new FTWords(info, ic.data, terms, m, ctx);
    ctx.ftOpt(tmp);
    return new FTIndexAccess(info, words, ic.data.meta.name, ic.iterable).iter(ctx);
  }