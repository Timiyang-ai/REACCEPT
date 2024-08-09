public static String replace(final String p, final InputSource input,
      final Context ctx, final boolean lock) throws BaseXException {

    final Data data = ctx.data();
    String path = path(p);
    if(path.isEmpty()) return Util.info(DIRERR, path);

    final byte[] src = token(path);
    final IntList docs = data.doc(p);
    // check if path was found
    if(docs.size() == 0) return Util.info(FILEWHICH, path);
    // check if path points exclusively to files
    for(int i = 0, is = docs.size(); i < is; i++) {
      if(!eq(data.text(docs.get(i), true), src)) return Util.info(DIRERR, path);
    }

    final String target;
    final int i = path.lastIndexOf('/');
    if(i != -1) {
      target = path.substring(0, i);
      path = path.substring(i + 1);
    } else {
      target = "";
    }

    try {
      if(lock) ctx.register(true);
      // add new document
      Add.add(path, target, input, ctx, null, false);
      // delete old documents if addition was successful
      for(int d = docs.size() - 1; d >= 0; d--) data.delete(docs.get(d));
      // flushes changes
      data.flush();
    } finally {
      if(lock) ctx.unregister(true);
    }
    return Util.info(PATHREPLACED, docs.size());
  }