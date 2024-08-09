private Item create(final QueryContext ctx) throws QueryException {
    final String name = string(checkStr(expr[0], ctx));
    if(!MetaData.validName(name, false)) BXDB_NAME.thrw(info, name);

    final ValueBuilder inputs = new ValueBuilder();
    final TokenList paths = new TokenList();
    if(expr.length > 1) {
      final Iter ir = ctx.iter(expr[1]);
      for(Item it; (it = ir.next()) != null;) inputs.add(it);
    }
    if(expr.length > 2) {
      final Iter ir = ctx.iter(expr[2]);
      for(Item it; (it = ir.next()) != null;) {
        final String path = string(checkStr(it, ctx));
        final String norm = MetaData.normPath(path);
        if(norm == null) RESINV.thrw(info, path);
        paths.add(norm);
      }
    }
    final long is = inputs.size();
    final int ps = paths.size();
    if(ps != 0 && is != ps) BXDB_CREATEARGS.thrw(info, is, ps);

    ctx.updates.add(new DBCreate(info, name, inputs, paths, ctx), ctx);
    return null;
  }