private Item assrt(final QueryContext ctx) throws QueryException {
    final byte[] str = expr.length < 2 ? null : checkStr(expr[1], ctx);
    if(expr[0].ebv(ctx, info).bool(info)) return null;
    throw str == null ? BXUN_ASSERT.thrw(info) : BXUN_ERROR.thrw(info, str);
  }