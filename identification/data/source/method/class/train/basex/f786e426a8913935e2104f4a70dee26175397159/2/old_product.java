private Item add(final QueryContext ctx) throws QueryException {
    final byte[] path;
    if(expr.length == 4) {
      final String s = ACreate.path(string(checkStr(expr[3], ctx)));
      path = token(s.isEmpty() ? s : s + '/');
    } else {
      path = null;
    }

    final NodeCache c = new NodeCache();
    final TokenList p = new TokenList();

    final Iter iter = ctx.iter(expr[1]);
    for(Item i; (i = iter.next()) != null;) {
      final ANode nd = checkNode(i);
      if(nd == null || nd.type != NodeType.DOC) UPFOTYPE.thrw(input, i);
      c.add(nd);
      p.add(EMPTY);
    }

    if(p.size() == 1) {
      if (expr.length > 2) {
        final byte[] name = checkStr(expr[2], ctx);
        if(path == null) p.set(name, 0);
        else {
          final TokenBuilder tb = new TokenBuilder();
          if(path.length > 0) tb.add(path);
          tb.add(name);
          p.set(tb.finish(), 0);
        }
      }
    } else if(p.size() > 1 && path != null) {
      for(int i = 0; i < p.size(); ++i) {
        byte[] doc = p.get(i);
        final int pos = lastIndexOf(doc, '/');
        if(pos > 0) doc = subtoken(doc, pos + 1);
        p.set(concat(path, doc), i);
      }
    }

    if(c.size() > 0) {
      final Data data = ctx.resource.data(checkStr(expr[0], ctx), input);
      ctx.updates.add(new Add(data, input, c, p, ctx.context), ctx);
    }
    return null;
  }