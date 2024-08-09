private Item replace(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final Data data = data(0, ctx);
    final byte[] trg = path(checkStr(expr[1], ctx));
    final Item doc = checkItem(expr[2], ctx);

    // collect all old documents
    final int[] old = data.doc(string(trg));
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

    final ArrayList<Item> docs = new ArrayList<Item>(1);
    docs.add(doc);
    final Add add = new Add(data, input, docs, trgname, trgpath, ctx.context);
    ctx.updates.add(add, ctx);

    return null;
  }