private ValueBuilder functions(final QueryContext ctx) throws QueryException {
    // about to be updated in a future version
    final ArrayList<StaticFunc> old = new ArrayList<StaticFunc>();
    if(expr.length > 0) {
      // cache existing functions
      for(final StaticFunc sf : ctx.funcs.funcs()) old.add(sf);
      try {
        final IO io = checkPath(expr[0], ctx);
        ctx.parse(Token.string(io.read()), io.path(), sc);
        ctx.compile();
      } catch(final IOException ex) {
        throw IOERR.get(info, ex);
      } finally {
        ctx.close();
      }
    }

    final ValueBuilder vb = new ValueBuilder();
    for(final StaticFunc sf : ctx.funcs.funcs()) {
      if(old.contains(sf)) continue;
      final FuncItem fi = Functions.getUser(sf, ctx, sf.sc, info);
      if(!fi.annotations().contains(Ann.Q_UPDATING)) vb.add(fi);
    }
    return vb;
  }