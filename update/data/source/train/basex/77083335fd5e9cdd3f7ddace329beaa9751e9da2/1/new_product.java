static Iter search(final Data data, final byte[] str, final Fun fun,
      final QueryContext ctx) throws QueryException {

    final IndexContext ic = new IndexContext(ctx, data, null, true);
    if(!data.meta.ftindex) NOIDX.thrw(fun.input, fun);

    final FTOpt tmp = ctx.ftopt;
    ctx.ftopt = new FTOpt();
    ctx.ftopt.set(CS, data.meta.casesens);
    ctx.ftopt.set(DC, data.meta.diacritics);
    ctx.ftopt.set(ST, data.meta.stemming);
    ctx.ftopt.ln = data.meta.language;
    final FTWords words = new FTWords(fun.input, ic.data, Str.get(str), ctx);
    ctx.ftopt = tmp;
    return new FTIndexAccess(fun.input, words, ic).iter(ctx);
  }