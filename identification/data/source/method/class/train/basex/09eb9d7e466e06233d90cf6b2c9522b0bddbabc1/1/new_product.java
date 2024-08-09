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

    Thread to = null;
    final Perm tmp = ctx.context.user.perm;
    if(expr.length > 2) {
      final Options opts = checkOptions(2, Q_OPTIONS, new XQueryOptions(), ctx);
      ctx.context.user.perm = Perm.get(opts.get(XQueryOptions.PERMISSION));
      final long ms = opts.get(XQueryOptions.TIMEOUT) * 1000l;
      if(ms != 0) {
        to = new Thread() {
          @Override
          public void run() {
            Performance.sleep(ms);
            qc.stop();
          }
        };
        to.setDaemon(false);
        to.start();
      }
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
    } catch(final ProcException ex) {
      throw BXXQ_STOPPED.get(info);
    } catch(final QueryException ex) {
      throw ex.err() == BASX_PERM ? BXXQ_PERM.get(info, ex.getLocalizedMessage()) : ex;
    } finally {
      ctx.context.user.perm = tmp;
      qc.close();
      if(to != null) to.interrupt();
    }
  }