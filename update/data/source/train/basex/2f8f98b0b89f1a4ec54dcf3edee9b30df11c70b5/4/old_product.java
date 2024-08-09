private Str info(final QueryContext ctx) throws QueryException {
    checkRead(ctx);

    final byte[] info;
    final Data d = ctx.resource.data(checkStr(expr[0], ctx), input);
    if(expr.length == 1) {
      final boolean create = ctx.context.user.perm(User.CREATE);
      info = InfoDB.db(d.meta, false, true, create);
    } else {
      final byte[] tp = checkStr(expr[1], ctx);
      final CmdIndexInfo cmd = InfoIndex.info(string(tp));
      if(cmd == null) NOIDX.thrw(input, this);
      info = InfoIndex.info(cmd, d);
    }
    return Str.get(Token.delete(info, '\r'));
  }