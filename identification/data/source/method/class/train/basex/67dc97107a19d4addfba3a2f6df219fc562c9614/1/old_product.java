private Iter trigger(final QueryContext ctx) throws QueryException {
    ctx.updating = true;
    Value v = expr[0].value(ctx);
    ctx.context.triggers.notify(ctx.context.session, expr[1].toString(),
        checkStr(expr[2], ctx));
    return v.iter();
  }