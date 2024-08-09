public static String replace(final String p, final InputSource input,
      final Context ctx, final boolean lock) throws BaseXException {

    final Data data = ctx.data();
    if(data == null) throw new BaseXException(PROCNODB);

    String path = IOFile.normalize(p);
    if(path.isEmpty()) throw new BaseXException(DIRERR, path);

    final byte[] src = token(path);
    final IntList docs = data.docs(p);
    // check if path was found
    if(docs.size() == 0) throw new BaseXException(FILEWHICH, path);
    // check if path points exclusively to files
    for(int i = 0, is = docs.size(); i < is; i++) {
      if(!eq(data.text(docs.get(i), true), src))
        throw new BaseXException(DIRERR, path);
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