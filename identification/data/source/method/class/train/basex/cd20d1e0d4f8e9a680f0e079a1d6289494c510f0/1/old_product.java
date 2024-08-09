protected final FElem parse(final QueryContext qc, final byte[] query, final String path)
      throws QueryException {

    boolean compile = false, plan = true;
    if(exprs.length > 1) {
      final Options opts = toOptions(1, Q_OPTIONS, new XQueryOptions(), qc);
      compile = opts.get(XQueryOptions.COMPILE);
      plan = opts.get(XQueryOptions.PLAN);
    }

    try(final QueryContext qctx = new QueryContext(qc.context)) {
      final StaticScope ss = qctx.parse(string(query), path, null);
      final boolean library = ss instanceof LibraryModule;

      final FElem root;
      if(library) {
        root = new FElem(LIBRARY_MODULE);
        final LibraryModule lib = (LibraryModule) ss;
        root.add(PREFIX, lib.name.string());
        root.add(URI, lib.name.uri());
      } else {
        root = new FElem(MAIN_MODULE);
        root.add(UPDATING, token(qctx.updating));
      }

      if(compile) qctx.compile();
      if(plan) root.add(qctx.plan());
      return root;
    } catch(final QueryException ex) {
      throw ex.info(info);
    }
  }