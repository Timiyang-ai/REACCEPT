private Iter logs(final QueryContext ctx) throws QueryException {
    final ValueBuilder vb = new ValueBuilder();
    if(expr.length == 0) {
      // return list of all log files
      for(final IOFile f : ctx.context.log.files()) {
        final String date = f.name().replace(IO.LOGSUFFIX, "");
        vb.add(new FElem(FILE).add(date).add(SIZE, Token.token(f.length())));
      }
    } else {
      // return content of single log file
      final boolean merge = expr.length > 1 && checkBln(expr[1], ctx);
      final String name = Token.string(checkStr(expr[0], ctx)) + IO.LOGSUFFIX;
      final IOFile file = new IOFile(ctx.context.log.dir(), name);
      final ArrayList<LogEntry> logs = logs(file);
      for(int s = 0; s < logs.size(); s++) {
        final LogEntry l1 = logs.get(s);
        final FElem elem = new FElem(ENTRY);
        if(l1.address != null) {
          if(merge && l1.ms.equals(BigDecimal.ZERO) && !Log.SERVER.equals(l1.address)) {
            for(int l = s + 1; l < logs.size(); l++) {
              final LogEntry l2 = logs.get(l);
              if(l2 != null && l1.address.equals(l2.address)) {
                if(l2.type.equals(Log.REQUEST)) continue;
                if(l1.type.equals(Log.REQUEST)) l1.type = "";
                l1.type = merge(l1.type, l2.type);
                l1.message = merge(l1.message, l2.message);
                l1.ms = l1.ms.add(l2.ms);
                logs.remove(l--);
                if(!l2.message.equals(Log.REQUEST)) break;
              }
            }
          }
          elem.add(TIME, l1.time).add(ADDRESS, l1.address).add(USER, l1.user);
          if(l1.type != null) elem.add(TYPE, l1.type);
          if(!l1.ms.equals(BigDecimal.ZERO)) elem.add(MS, l1.ms.toString());
          if(l1.message != null) elem.add(l1.message);
        } else {
          elem.add(l1.message);
        }
        vb.add(elem);
      }
    }
    return vb;
  }