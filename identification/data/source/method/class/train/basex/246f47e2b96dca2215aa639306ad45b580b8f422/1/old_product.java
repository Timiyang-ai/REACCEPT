private Iter event(final QueryContext ctx) throws QueryException {
    String msg = expr[3].toString().equals("m") ? expr[2].toString() : null;
    Value v = expr[0].value(ctx);
    if(msg == null) {
      ctx.updating = true;
      msg = ctx.value(expr[0]).toString();
    }
    ctx.context.events.notify(ctx.context.session, expr[1].toString(), msg);
    return v.iter();
  }