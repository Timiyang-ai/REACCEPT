private void query(final ServerCmd sc) throws IOException {
    // iterator argument (query or identifier)
    String arg = in.readString();

    QueryListener qp = null;
    String err = null;
    try {
      if(sc == QUERY) {
        final String query = arg;
        qp = new QueryListener(query, out, context);
        arg = Integer.toString(id++);
        queries.put(arg, qp);
        // send {ID}0
        out.writeString(arg);
        // write log file
        log.write(this, sc + "(" + arg + ")", query, OK);
      } else {
        // find query process
        qp = queries.get(arg);

        // ID has already been removed
        if(qp == null) {
          if(sc != CLOSE) throw new IOException("Unknown Query ID: " + arg);
        } else if(sc == BIND) {
          final String key = in.readString();
          final String val = in.readString();
          final String typ = in.readString();
          qp.bind(key, val, typ);
          log.write(this, sc + "(" + arg + ")", key, val, typ, OK);
        } else if(sc == NEXT) {
          qp.next();
        } else if(sc == EXEC) {
          qp.execute();
        } else if(sc == INFO) {
          out.print(qp.info());
        } else if(sc == OPTIONS) {
          out.print(qp.options());
        } else if(sc == CLOSE) {
          qp.close();
          queries.remove(arg);
        }
        // send 0 as end marker
        out.write(0);
      }
      // send 0 as success flag
      out.write(0);

      // write log file (skip next calls; bind has been logged before)
      if(sc != NEXT && sc != BIND) log.write(this, sc + "(" + arg + ")", OK);
    } catch(final Exception ex) {
      // log exception (static or runtime)
      err = ex.getMessage();
      log.write(this, sc + "(" + arg + ")", INFOERROR + err);
      if(qp != null) qp.close();
      queries.remove(arg);
    }
    if(err != null) {
      // send 0 as end marker, 1 as error flag, and {MSG}0
      out.write(0);
      out.write(1);
      out.writeString(err);
    }
    out.flush();
  }