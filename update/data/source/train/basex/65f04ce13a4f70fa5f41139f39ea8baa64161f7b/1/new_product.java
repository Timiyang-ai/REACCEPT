private ValueBuilder eval(final QueryContext ctx, final byte[] qu, final String path,
      final boolean openDB) throws QueryException {

    // bind variables and context item
    final HashMap<String, Value> bindings = bindings(1, ctx);

    final QueryContext qc = ctx.proc(new QueryContext(ctx));
    qc.resource.openDB = openDB;

    final Timer to = new Timer(true);
    final Perm tmp = ctx.context.user.perm;
    if(expr.length > 2) {
      final Options opts = checkOptions(2, Q_OPTIONS, new XQueryOptions(), ctx);
      ctx.context.user.perm = Perm.get(opts.get(XQueryOptions.PERMISSION));
      // initial memory consumption: perform garbage collection and calculate usage
      Performance.gc(2);
      final long mb = opts.get(XQueryOptions.MEMORY);
      if(mb != 0) {
        final long limit = Performance.memory() + (mb << 20);
        to.schedule(new TimerTask() {
          @Override
          public void run() {
            // limit reached: perform garbage collection and check again
            if(Performance.memory() > limit) {
              Performance.gc(1);
              if(Performance.memory() > limit) qc.stop();
            }
          }
        }, 500, 500);
      }
      final long ms = opts.get(XQueryOptions.TIMEOUT) * 1000L;
      if(ms != 0) {
        to.schedule(new TimerTask() {
          @Override
          public void run() { qc.stop(); }
        }, ms);
      }
    }

    // evaluate query
    try {
      final StaticContext sctx = new StaticContext(qc.context.options.get(MainOptions.XQUERY3));
      for(final Map.Entry<String, Value> it : bindings.entrySet()) {
        final String k = it.getKey();
        final Value v = it.getValue();
        if(k.isEmpty()) qc.context(v, null, sctx);
        else qc.bind(k, v, null);
      }

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
      ctx.proc(null);
      qc.close();
      to.cancel();
    }
  }