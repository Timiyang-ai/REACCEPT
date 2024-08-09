private Item function(final QueryContext ctx) throws QueryException {
    final FItem fn = checkFunc(exprs[0], ctx);
    final QNm name = fn.funcName();
    final StaticFunc sf = name == null ? null : ctx.funcs.get(name, fn.arity(), null, false);
    return new PlainDoc(ctx, info).function(name, sf, fn.funcType(), fn.annotations(), null);
  }