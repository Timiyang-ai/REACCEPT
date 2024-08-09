private Item assrt(final QueryContext ctx) throws QueryException {
    final byte[] str = expr.length < 2 ? null : checkStr(expr[1], ctx);
    if(expr[0].ebv(ctx, info).bool(info)) return null;
    throw str == null ? UNIT_ASSERT.thrw(info) : UNIT_MESSAGE.thrw(info, str);
  }