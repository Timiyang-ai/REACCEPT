private Str info(final QueryContext ctx) throws QueryException {
    checkRead(ctx);

    final Data data = data(0, ctx);
    final byte[] info;
    if(expr.length == 1) {
      final boolean create = ctx.context.user.perm(User.CREATE);
      info = InfoDB.db(data.meta, false, true, create);
    } else {
      final byte[] tp = checkStr(expr[1], ctx);
      final CmdIndexInfo cmd = InfoIndex.info(string(tp));
      if(cmd == null) NOIDX.thrw(input, this);
      info = InfoIndex.info(cmd, data);
    }
    return Str.get(Token.delete(info, '\r'));
  }