private Item optimize(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    final boolean all = expr.length == 2 ? checkBln(expr[1], ctx) : false;
    ctx.resource.data(checkStr(expr[0], ctx), input);
    try {
      (all ? new OptimizeAll() : new Optimize()).execute(ctx.context);
    } catch(final BaseXException e) {
      Util.errln(e);
    }
    return null;
  }