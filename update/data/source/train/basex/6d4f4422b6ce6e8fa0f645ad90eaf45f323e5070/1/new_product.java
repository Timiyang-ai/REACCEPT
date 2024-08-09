public static String replace(final String p, final InputSource input,
      final Context ctx, final boolean lock) throws BaseXException {

    final Data data = ctx.data();
    if(data == null) throw new BaseXException(PROCNODB);

    String path = IOFile.normalize(p);
    String target = "";
    if(path.isEmpty()) throw new BaseXException(DIRERR, path);

    final byte[] src = token(path);
    final IntList docs = data.docs(p);
    final int is = docs.size();
    // check if path points exclusively to files
    for(int i = 0; i < is; i++) {
      if(!eq(data.text(docs.get(i), true), src))
        throw new BaseXException(DIRERR, path);
    }

    final int i = path.lastIndexOf('/');
    if(i != -1) {
      target = path.substring(0, i);
      path = path.substring(i + 1);
    }

    try {
      if(lock) ctx.register(true);
      // replace document
      if(docs.size() > 0) {
        // add new document
        Add.add(path, target, input, ctx, null, false);
        // delete old documents if addition was successful
        for(int d = docs.size() - 1; d >= 0; d--) data.delete(docs.get(d));
        // flushes changes
        data.flush();
      }
      // replace binary
      if(!replace(data, path, input)) throw new BaseXException(PARSEERR, path);
    } finally {
      if(lock) ctx.unregister(true);
    }
    return Util.info(PATHREPLACED, docs.size());
  }