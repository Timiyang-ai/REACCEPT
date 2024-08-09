protected final FElem parse(final QueryContext qc, final byte[] query, final String path)
      throws QueryException {

    boolean compile = false, plan = true, pass = false;
    if(exprs.length > 1) {
      final Options opts = toOptions(1, new XQueryOptions(), qc);
      compile = opts.get(XQueryOptions.COMPILE);
      plan = opts.get(XQueryOptions.PLAN);
      pass = opts.get(XQueryOptions.PASS);
    }

    try(QueryContext qctx = new QueryContext(qc.context)) {
      final Module mod = qctx.parse(string(query), path, null);
      final boolean library = mod instanceof LibraryModule;

      final FElem root;
      if(library) {
        root = new FElem(LIBRARY_MODULE);
        final LibraryModule lib = (LibraryModule) mod;
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
      if(!pass) ex.info(info);
      throw ex;
    }
  }