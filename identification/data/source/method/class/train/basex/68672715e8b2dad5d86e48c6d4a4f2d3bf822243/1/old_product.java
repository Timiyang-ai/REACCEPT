private Iter event(final QueryContext ctx) throws QueryException {
    String name = expr[0].toString();
    String q2 = expr[2].toString();
    String msg = "";
    Value v = expr[1].value(ctx);
    if(q2.replaceAll("\"", "").trim().isEmpty()) {
      ctx.updating = true;
      msg = ctx.value(expr[1]).toString();
    } else {
      msg = ctx.value(expr[2]).toString();
    }
    ctx.context.events.notify(ctx.context.session, name, msg);
    return v.iter();
  }