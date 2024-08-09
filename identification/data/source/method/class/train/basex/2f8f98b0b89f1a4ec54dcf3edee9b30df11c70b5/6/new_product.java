private Item optimize(final QueryContext ctx) throws QueryException {
    checkWrite(ctx);

    // [DP] I've rewritten this a little; yet, the optimize code will
    //   probably have to be moved to static methods to avoid that
    //   the command is executed on the global data instance.

    final boolean all = expr.length == 2 && checkBln(expr[1], ctx);
    data(0, ctx);
    final Command cmd = all ? new OptimizeAll() : new Optimize();
    if(!cmd.run(ctx.context)) DBERR.thrw(input, cmd.info());
    return null;
  }