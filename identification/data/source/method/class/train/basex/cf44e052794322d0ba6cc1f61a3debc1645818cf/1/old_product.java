private Iter event(final QueryContext ctx) throws QueryException {
    String name = expr[0].toString();
    String msg = "";
    Value v = expr[1].value(ctx);
    if(expr.length < 3) {
      ctx.updating = true;
      msg = ctx.value(expr[1]).toString();
    } else {
      msg = ctx.value(expr[2]).toString();
    }
    ctx.context.events.notify(ctx.context.session, name, msg);
    return v.iter();
  }