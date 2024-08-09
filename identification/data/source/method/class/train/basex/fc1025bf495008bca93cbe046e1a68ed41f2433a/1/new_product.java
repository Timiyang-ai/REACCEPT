private Iter backups(final QueryContext ctx) throws QueryException {
    checkCreate(ctx);
    final String prefix = expr.length == 0 ? null : string(checkStr(expr[0], ctx)) + '-';

    final StringList list = ctx.context.databases.backups(prefix);
    final IOFile dbpath = ctx.context.globalopts.dbpath();
    return new Iter() {
      int up = -1;

      @Override
      public Item next() {
        if(++up >= list.size()) return null;
        final String name = list.get(up);
        final long length = new IOFile(dbpath, name).length();
        return new FElem(BACKUP).add(name).add(SIZE, token(length));
      }
    };
  }