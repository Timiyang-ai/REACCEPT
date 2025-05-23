private Item create(final QueryContext ctx) throws QueryException {
    final String name = string(checkStr(expr[0], ctx));
    if(!Databases.validName(name)) BXDB_NAME.thrw(info, name);

    final TokenList paths = new TokenList();
    if(expr.length > 2) {
      final Iter ir = ctx.iter(expr[2]);
      for(Item it; (it = ir.next()) != null;) {
        final String path = string(checkStr(it));
        final String norm = MetaData.normPath(path);
        if(norm == null) RESINV.thrw(info, path);
        paths.add(norm);
      }
    }

    final int ps = paths.size();
    final List<NewInput> inputs = new ArrayList<NewInput>(ps);
    if(expr.length > 1) {
      final Value val = ctx.value(expr[1]);
      // number of specified inputs and paths must be identical
      final long is = val.size();
      if(ps != 0 && is != ps) BXDB_CREATEARGS.thrw(info, is, ps);

      for(int i = 0; i < is; i++) {
        final byte[] path = i < ps ? paths.get(i) : Token.EMPTY;
        inputs.add(checkInput(val.itemAt(i), path));
      }
    }

    final Item opt = expr.length > 3 ? expr[3].item(ctx, info) : null;
    final TokenMap map = new FuncParams(Q_OPTIONS, info).parse(opt);
    ctx.updates.add(new DBCreate(info, name, inputs, map, ctx), ctx);
    return null;
  }