public void module(final byte[] path, final byte[] uri) throws QueryException {
    // get absolute path
    final IO io = sc.io(string(path));
    final byte[] p = token(io.path());

    // check if module has already been parsed
    final byte[] u = qc.modParsed.get(p);
    if(u != null) {
      if(!eq(uri, u)) throw error(WRONGMODULE_X_X, uri,
          qc.context.user.has(Perm.ADMIN) ? io.path() : io.name());
      if(!sc.xquery3() && qc.modStack.contains(p)) throw error(CIRCMODULE);
      return;
    }
    qc.modParsed.put(p, uri);

    // read module
    final String qu;
    try {
      qu = string(io.read());
    } catch(final IOException ex) {
      throw error(WHICHMODFILE_X, qc.context.user.has(Perm.ADMIN) ? io.path() : io.name());
    }

    qc.modStack.push(p);
    final StaticContext sub = new StaticContext(qc.context);
    final LibraryModule lib = new QueryParser(qu, io.path(), qc, sub).parseLibrary(false);
    final byte[] muri = lib.name.uri();

    // check if import and declaration uri match
    if(!eq(uri, muri)) throw error(WRONGMODULE_X_X, muri, file);

    // check if context value declaration types are compatible to each other
    if(sub.contextType != null) {
      if(sc.contextType == null) {
        sc.contextType = sub.contextType;
      } else if(!sub.contextType.eq(sc.contextType)) {
        throw error(CITYPES_X_X, sub.contextType, sc.contextType);
      }
    }
    qc.modStack.pop();
  }