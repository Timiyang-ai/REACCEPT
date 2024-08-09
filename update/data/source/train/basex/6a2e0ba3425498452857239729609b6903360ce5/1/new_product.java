protected final FElem parse(final QueryContext qc, final byte[] query, final String path)
      throws QueryException {

    XQueryOptions opts = new XQueryOptions();
    if(exprs.length > 1) toOptions(1, opts, qc);

    // base-uri: adopt specified uri, passed on uri, or uri from parent query
    final String bu = opts.get(XQueryOptions.BASE_URI);
    final String uri = bu != null ? bu : path != null ? path : string(sc.baseURI().string());

    try(QueryContext qctx = new QueryContext(qc.context)) {
      final Module mod = qctx.parse(string(query), uri, null);
      final FElem root;
      if(mod instanceof LibraryModule) {
        final LibraryModule lib = (LibraryModule) mod;
        root = new FElem(LIBRARY_MODULE);
        root.add(PREFIX, lib.name.string());
        root.add(URI, lib.name.uri());
      } else {
        root = new FElem(MAIN_MODULE);
        root.add(UPDATING, token(qctx.updating));
      }

      if(opts.get(XQueryOptions.COMPILE)) qctx.compile();
      if(opts.get(XQueryOptions.PLAN)) root.add(qctx.plan());
      return root;
    } catch(final QueryException ex) {
      if(!opts.get(XQueryOptions.PASS)) ex.info(info);
      throw ex;
    }
  }