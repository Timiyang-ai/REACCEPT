private Iter search(final QueryContext qc) throws QueryException {
    final Data data = checkData(qc);
    final Value terms = qc.value(exprs[1]);
    final FTOptions opts = checkOptions(2, Q_OPTIONS, new FTOptions(), qc);

    final IndexContext ic = new IndexContext(data, false);
    if(!data.meta.ftxtindex) throw BXDB_INDEX.get(info, data.meta.name,
        IndexType.FULLTEXT.toString().toLowerCase(Locale.ENGLISH));

    final FTOpt opt = new FTOpt().copy(data.meta);
    final FTMode mode = opts.get(FTIndexOptions.MODE);
    opt.set(FZ, opts.get(FTIndexOptions.FUZZY));
    opt.set(WC, opts.get(FTIndexOptions.WILDCARDS));
    if(opt.is(FZ) && opt.is(WC)) throw BXFT_MATCH.get(info, this);

    final FTOpt tmp = qc.ftOpt();
    qc.ftOpt(opt);
    final FTExpr fte = new FTWords(info, data, terms, mode).compile(qc, null);
    qc.ftOpt(tmp);
    return new FTIndexAccess(info, options(fte, opts), ic).iter(qc);
  }