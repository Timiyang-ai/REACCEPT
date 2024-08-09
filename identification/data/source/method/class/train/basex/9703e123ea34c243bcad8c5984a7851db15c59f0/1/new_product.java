protected final TokenList tokens(final QueryContext qc, final boolean all) throws QueryException {
    final Item item = exprs[0].atomItem(qc, info);
    final FtTokenizeOptions opts = toOptions(1, new FtTokenizeOptions(), qc);

    final TokenList tl = new TokenList();
    if(item != null) {
      final FTOpt opt = new FTOpt().assign(qc.ftOpt());
      final FTDiacritics dc = opts.get(FtTokenizeOptions.DIACRITICS);
      if(dc != null) opt.set(DC, dc == FTDiacritics.SENSITIVE);
      final Boolean st = opts.get(FtTokenizeOptions.STEMMING);
      if(st != null) opt.set(ST, st);
      final String ln = opts.get(FtTokenizeOptions.LANGUAGE);
      if(ln != null) opt.ln = Language.get(ln);
      final FTCase cs = opts.get(FtTokenizeOptions.CASE);
      if(cs != null) opt.cs = cs;

      final FTLexer lexer = new FTLexer(opt).init(toToken(item));
      if(all) lexer.all();
      while(lexer.hasNext()) tl.add(lexer.nextToken());
    }
    return tl;
  }