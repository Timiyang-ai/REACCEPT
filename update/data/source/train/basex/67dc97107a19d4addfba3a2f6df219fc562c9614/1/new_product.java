private Iter trigger(final QueryContext ctx) throws QueryException {
    String msg = expr[3].toString().equals("m") ? expr[2].toString() : null;
    Value v = expr[0].value(ctx);
    if(msg == null) {
      ctx.updating = true;
      msg = ItemCache.get(v.iter()).toString();
    }
    ctx.context.triggers.notify(ctx.context.session, expr[1].toString(), msg);
    return v.iter();
  }