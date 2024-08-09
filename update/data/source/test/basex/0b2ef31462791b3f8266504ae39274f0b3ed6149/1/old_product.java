private Item create(final QueryContext ctx) throws QueryException {
    final String name = string(checkStr(expr[0], ctx));
    if(!Databases.validName(name)) throw BXDB_NAME.get(info, name);

    final TokenList paths = new TokenList();
    if(expr.length > 2) {
      final Iter ir = ctx.iter(expr[2]);
      for(Item it; (it = ir.next()) != null;) {
        final String path = string(checkStr(it));
        final String norm = MetaData.normPath(path);
        if(norm == null) throw RESINV.get(info, path);
        paths.add(norm);
      }
    }

    final int ps = paths.size();
    final List<NewInput> inputs = new ArrayList<NewInput>(ps);
    if(expr.length > 1) {
      final Value val = ctx.value(expr[1]);
      // number of specified inputs and paths must be identical
      final long is = val.size();
      if(ps != 0 && is != ps) throw BXDB_CREATEARGS.get(info, is, ps);

      for(int i = 0; i < is; i++) {
        final byte[] path = i < ps ? paths.get(i) : Token.EMPTY;
        inputs.add(checkInput(val.itemAt(i), path));
      }
    }

    final Options opts = checkOptions(3, Q_OPTIONS, new Options(), ctx);
    ctx.updates.add(new DBCreate(info, name, inputs, opts, ctx), ctx);
    return null;
  }