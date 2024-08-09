final ItemList eval(final QueryContext qc, final byte[] qu, final String path,
      final boolean updating) throws QueryException {

    // bind variables and context value
    final HashMap<String, Value> bindings = toBindings(1, qc);
    final User user = qc.context.user();
    final Perm tmp = user.perm("");
    Timer to = null;

    try(final QueryContext qctx = qc.proc(new QueryContext(qc))) {
      if(exprs.length > 2) {
        final Options opts = toOptions(2, Q_OPTIONS, new XQueryOptions(), qc);
        final Perm perm = Perm.get(opts.get(XQueryOptions.PERMISSION).toString());
        if(!user.has(perm)) throw BXXQ_PERM2_X.get(info, perm);
        user.perm(perm);

        // initial memory consumption: enforce garbage collection and calculate usage
        final long mb = opts.get(XQueryOptions.MEMORY);
        if(mb != 0) {
          Performance.gc(2);
          final long limit = Performance.memory() + (mb << 20);
          to = new Timer(true);
          to.schedule(new TimerTask() {
            @Override
            public void run() {
              // limit reached: perform garbage collection and check again
              if(Performance.memory() > limit) qctx.stop();
            }
          }, 500, 500);
        }
        final long ms = opts.get(XQueryOptions.TIMEOUT) * 1000L;
        if(ms != 0) {
          if(to == null) to = new Timer(true);
          to.schedule(new TimerTask() {
            @Override
            public void run() { qctx.stop(); }
          }, ms);
        }
      }

      // evaluate query
      try {
        final StaticContext sctx = new StaticContext(qctx);
        for(final Entry<String, Value> it : bindings.entrySet()) {
          final String key = it.getKey();
          final Value val = it.getValue();
          if(key.isEmpty()) qctx.context(val, sctx);
          else qctx.bind(key, val, sctx);
        }
        qctx.parseMain(string(qu), path, sctx);

        if(updating) {
          if(!sc.mixUpdates && !qctx.updating && !qctx.root.expr.isVacuous())
            throw BXXQ_NOUPDATE.get(info);
        } else {
          if(qctx.updating) throw BXXQ_UPDATING.get(info);
        }

        final ItemList cache = new ItemList();
        final Iter iter = qctx.iter();
        for(Item it; (it = iter.next()) != null;) {
          qc.checkStop();
          cache.add(it);
        }
        return cache;
      } catch(final ProcException ex) {
        throw BXXQ_STOPPED.get(info);
      } catch(final QueryException ex) {
        throw ex.error() == BASX_PERM_X ? BXXQ_PERM_X.get(info, ex.getLocalizedMessage()) :
          ex.info(info);
      }

    } finally {
      if(to != null) to.cancel();
      user.perm(tmp, "");
      qc.proc(null);
    }
  }