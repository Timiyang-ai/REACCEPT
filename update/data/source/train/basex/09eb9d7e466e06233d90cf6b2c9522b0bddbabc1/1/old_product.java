private ValueBuilder eval(final QueryContext ctx, final byte[] qu, final String path,
      final boolean openDB) throws QueryException {

    final QueryContext qc = new QueryContext(ctx);
    qc.resource.openDB = openDB;
    final StaticContext sctx = new StaticContext(qc.context.options.get(MainOptions.XQUERY3));

    // bind variables and context item
    for(final Map.Entry<String, Value> it : bindings(1, ctx).entrySet()) {
      final String k = it.getKey();
      final Value v = it.getValue();
      if(k.isEmpty()) qc.context(v, null, sctx);
      else qc.bind(k, v, null);
    }
    // evaluate query
    try {
      qc.parseMain(string(qu), path, sctx);
      if(qc.updating) throw BXXQ_UPDATING.get(info);
      qc.compile();

      final ValueBuilder vb = new ValueBuilder();
      final Iter iter = qc.iter();
      if(openDB) {
        cache(iter, vb, ctx);
      } else {
        for(Item it; (it = iter.next()) != null;) {
          if(it instanceof FItem) throw FIVALUE.get(info, it.type);
          vb.add(it);
        }
      }
      return vb;
    } finally {
      qc.close();
    }
  }