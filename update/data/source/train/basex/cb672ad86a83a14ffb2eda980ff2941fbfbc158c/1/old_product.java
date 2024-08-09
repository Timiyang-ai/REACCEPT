private void query(final ServerCmd sc) throws IOException {
    // iterator argument
    String arg = in.readString();

    QueryProcess qp = null;
    String err = null;
    try {
      if(sc == QUERY) {
        qp = new QueryProcess(arg, out, context);
        arg = Integer.toString(id++);
        queries.put(arg, qp);

        log.write(this, arg, qp.query, OK);
        // send {ID}0
        out.writeString(arg);
      } else {
        // find query process
        qp = queries.get(arg);
        // ID has already been removed
        if(qp == null && sc != CLOSE)
          throw new IOException("Unknown query ID (" + arg + ")");
        if(sc == BIND) {
          qp.bind(in.readString(), in.readString(), in.readString());
        } else if(sc == INIT) {
          qp.init();
        } else if(sc == NEXT) {
          qp.next();
        } else if(sc == CLOSE && qp != null) {
          qp.close(false);
          queries.remove(arg);
        } else if(sc == EXEC) {
          qp.execute();
        }
        // send 0 as end marker
        out.write(0);
      }
      // send 0 as success flag
      out.write(0);
    } catch(final Exception ex) {
      // log exception (static or runtime)
      err = ex.getMessage();
      if(qp != null) qp.close(true);
      queries.remove(arg);
    }
    if(err != null) {
      // send 0 as end marker, 1 as error flag, and {MSG}0
      log.write(this, arg, INFOERROR + err);
      out.write(0);
      out.write(1);
      out.writeString(err);
    }
    out.flush();
  }