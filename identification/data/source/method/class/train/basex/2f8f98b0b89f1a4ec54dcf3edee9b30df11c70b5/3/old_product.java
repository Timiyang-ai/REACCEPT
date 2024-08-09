private DBNode open(final QueryContext ctx, final boolean id)
      throws QueryException {

    final Data data = ctx.resource.data(checkStr(expr[0], ctx), input);
    final int v = (int) checkItr(expr[1], ctx);
    final int pre = id ? data.pre(v) : v;
    if(pre < 0 || pre >= data.meta.size) IDINVALID.thrw(input, this, v);
    return new DBNode(data, pre);
  }