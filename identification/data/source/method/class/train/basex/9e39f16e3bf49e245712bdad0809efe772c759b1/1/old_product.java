private Item function(final QueryContext ctx) throws QueryException {
    final FItem f = checkFunc(expr[0], ctx);
    final StaticFunc sf = f.funcName() == null ? null :
      ctx.funcs.get(f.funcName(), f.arity(), null, false);
    return new PlainDoc(ctx, info).function(f.funcName(), sf, f.funcType(), null);
  }