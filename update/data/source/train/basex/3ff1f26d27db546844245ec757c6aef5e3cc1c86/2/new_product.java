private ANode doc(final QueryContext ctx) throws QueryException {
    final Item it = exprs[0].item(ctx, info);
    if(it == null) return null;
    final byte[] in = checkEStr(it);
    if(!Uri.uri(in).isValid()) throw INVDOC.get(info, in);
    return ctx.resources.doc(new QueryInput(string(in)), sc.baseIO(), info);
  }