private Item function(final QueryContext ctx) throws QueryException {
    final FItem func = checkFunc(expr[0], ctx);
    final QNm name = func.funcName();
    final StaticFunc sf = name == null ? null : ctx.funcs.get(name, func.arity(), null, false);
    return new PlainDoc(ctx, info).function(name, sf, func.funcType(), func.annotations(), null);
  }