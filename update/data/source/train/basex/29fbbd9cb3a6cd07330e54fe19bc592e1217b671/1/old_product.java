private Iter logs(final QueryContext ctx) throws QueryException {
    final ValueBuilder vb = new ValueBuilder();
    if(expr.length == 0) {
      // return list of all log files
      for(final IOFile f : ctx.context.log.files()) {
        final String date = f.name().replace(IO.LOGSUFFIX, "");
        vb.add(new FElem(FILE).add(date).add(SIZE, Token.token(f.length())));
      }
    } else {
      // return log file contents
      final String name = Token.string(checkStr(expr[0], ctx)) + IO.LOGSUFFIX;
      final IOFile file = new IOFile(ctx.context.log.dir(), name);
      if(file.exists()) {
        try {
          final NewlineInput nli = new NewlineInput(file);
          try {
            for(String l; (l = nli.readLine()) != null;) {
              final FElem elem = new FElem(ENTRY);
              final String[] cols = l.split("\t");
              if(cols.length > 2 && (cols[1].matches(".*:\\d+") ||
                  cols[1].equals(Log.SERVER))) {
                // new format (more specific)
                elem.add(TIME, cols[0]).add(ADDRESS, cols[1]).add(USER, cols[2]);
                if(cols.length > 3) elem.add(TYPE, cols[3].toLowerCase(Locale.ENGLISH));
                if(cols.length > 4) elem.add(cols[4]);
                if(cols.length > 5) elem.add(MS, cols[5].replace(" ms", ""));
              } else {
                elem.add(l);
              }
              vb.add(elem);
            }
          } finally {
            nli.close();
          }
        } catch(final IOException ex) {
          throw IOERR.get(info, ex);
        }
      }
    }
    return vb;
  }