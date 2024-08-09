private void module(final byte[] path, final byte[] uri, final boolean imprt)
      throws QueryException {
    // get absolute path
    final IO io = ctx.sc.io(string(path));
    final byte[] p = token(io.path());

    // check if module has already been parsed
    final byte[] u = ctx.modParsed.get(p);
    if(u != null) {
      if(!eq(uri, u)) error(WRONGMODULE, uri,
          ctx.context.user.has(Perm.ADMIN) ? io.path() : io.name());
      if(!ctx.sc.xquery3() && ctx.modStack.contains(p)) error(CIRCMODULE);
      return;
    }
    ctx.modParsed.put(p, uri);

    // read module
    String qu = null;
    try {
      qu = string(io.read());
    } catch(final IOException ex) {
      error(WHICHMODFILE, ctx.context.user.has(Perm.ADMIN) ? io.path() : io.name());
    }

    ctx.modStack.push(p);
    final StaticContext sc = ctx.sc;
    ctx.sc = new StaticContext(sc.xquery3());
    final LibraryModule lib = new QueryParser(qu, io.path(), ctx).parseLibrary(!imprt);
    final byte[] muri = lib.name.uri();

    // check if import and declaration uri match
    if(!eq(uri, muri)) error(WRONGMODULE, muri, file);

    // check if context item declaration types are compatible to each other
    if(ctx.sc.initType != null) {
      if(sc.initType == null) {
        sc.initType = ctx.sc.initType;
      } else if(!ctx.sc.initType.eq(sc.initType)) {
        error(CITYPES, ctx.sc.initType, sc.initType);
      }
    }
    ctx.sc = sc;
    ctx.modStack.pop();
  }