final ItemList eval(final QueryContext qc, final String query, final String path,
      final boolean updating) throws QueryException {

    // bind variables and context value
    final HashMap<String, Value> bindings = toBindings(1, qc);
    final Options opts = new XQueryOptions();
    if(exprs.length > 2) toOptions(2, opts, qc);

    // allow limited number of nested calls
    QueryContext qcAnc = qc;
    for(int c = 5; qcAnc != null && c > 0; c--) qcAnc = qcAnc.parent;
    if(qcAnc != null) throw BXXQ_NESTED.get(info);

    final User user = qc.context.user();
    final Perm tmp = user.perm("");
    Timer to = null;

    final Perm perm = Perm.get(opts.get(XQueryOptions.PERMISSION).toString());
    if(!user.has(perm)) throw BXXQ_PERM2_X.get(info, perm);
    user.perm(perm);

    try(QueryContext qctx = new QueryContext(qc)) {
      // limit memory consumption: enforce garbage collection and calculate usage
      final long mb = opts.get(XQueryOptions.MEMORY);
      if(mb != 0) {
        Performance.gc(2);
        final long limit = Performance.memory() + (mb << 20);
        to = new Timer(true);
        to.schedule(new TimerTask() {
          @Override
          // limit reached: stop query
          public void run() { if(Performance.memory() > limit) qctx.memory(); }
        }, 500, 500);
      }

      // timeout
      final long ms = opts.get(XQueryOptions.TIMEOUT) * 1000L;
      if(ms != 0) {
        if(to == null) to = new Timer(true);
        to.schedule(new TimerTask() {
          @Override
          public void run() { qctx.timeout(); }
        }, ms);
      }

      // base-uri: adopt specified uri, passed on uri, or uri from parent query
      final String bu = opts.get(XQueryOptions.BASE_URI);
      final String uri = bu != null ? bu : path != null ? path : string(sc.baseURI().string());

      // evaluate query
      try {
        final StaticContext sctx = new StaticContext(qctx);
        sctx.baseURI(uri);
        for(final Entry<String, Value> it : bindings.entrySet()) {
          final String key = it.getKey();
          final Value val = it.getValue();
          if(key.isEmpty()) qctx.context(val, sctx);
          else qctx.bind(key, val, sctx);
        }
        qctx.parseMain(query, null, sctx);

        if(updating) {
          if(!sc.mixUpdates && !qctx.updating && !qctx.root.expr.isVacuous())
            throw BXXQ_NOUPDATE.get(info);
        } else {
          if(qctx.updating) throw BXXQ_UPDATING.get(info);
        }

        final ItemList cache = new ItemList();
        final Iter iter = qctx.iter();
        for(Item it; (it = iter.next()) != null;) {
          qctx.checkStop();
          qc.checkStop();
          cache.add(it);
        }
        return cache;
      } catch(final JobException ex) {
        if(qctx.state == JobState.TIMEOUT) throw BXXQ_TIMEOUT.get(info);
        if(qctx.state == JobState.MEMORY)  throw BXXQ_MEMORY.get(info);
        throw ex;
      } catch(final QueryException ex) {
        if(ex.error() == BASX_PERM_X) throw BXXQ_PERM_X.get(info, ex.getLocalizedMessage());
        if(!opts.get(XQueryOptions.PASS)) ex.info(info);
        throw ex;
      }
    } finally {
      if(to != null) to.cancel();
      user.perm(tmp, "");
    }
  }