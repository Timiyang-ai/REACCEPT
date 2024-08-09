private Item replace(final QueryContext ctx) throws QueryException {
    final String path = path(string(checkStr(expr[0], ctx)));

    // the first step of the path should be the database name
    final int pos = path.indexOf('/');
    if(pos <= 0) NODB.thrw(input, path);
    final byte[] db = token(path.substring(0, pos));
    final Data data = ctx.resource.data(db, input);

    // replace: source and target path are the same
    final String src = path.substring(pos + 1);
    final byte[] trg = token(src);

    final Item doc = checkItem(expr[1], ctx);

    // collect all old documents
    final int[] old = data.doc(src);
    if(old.length > 0) {
      final int pre = old[0];
      if(old.length > 1 || !eq(data.text(pre, true), trg))
        DOCTRGMULT.thrw(input);
      ctx.updates.add(new DeleteNode(pre, data, input), ctx);
    }

    final byte[] trgname;
    final byte[] trgpath;
    final int p = lastIndexOf(trg, '/');
    if(p < 0) {
      trgname = trg;
      trgpath = null;
    } else {
      trgname = subtoken(trg, p + 1);
      trgpath = subtoken(trg, 0, p);
    }

    final ArrayList<Item> docs = new ArrayList<Item>(); docs.add(doc);
    final Add add = new Add(data, input, docs, trgname, trgpath, ctx.context);
    ctx.updates.add(add, ctx);

    return null;
  }