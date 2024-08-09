void query(final String path) throws IOException {
    // set base path and serialization parameters
    final HTTPContext http = session.http;
    context.options.set(MainOptions.QUERYPATH, path);
    context.options.set(MainOptions.SERIALIZER, http.sopts());
    http.initResponse();

    final int cs = cmds.size();
    for(int c = 0; c < cs; c++) {
      final Command cmd = cmds.get(c);
      if(cmd instanceof XQuery) {
        final XQuery xq = (XQuery) cmds.get(c);
        // create query instance
        if(value != null) xq.bind(null, value, NodeType.DOC.toString());

        // bind HTTP context and external variables
        xq.http(http);
        for(final Entry<String, String[]> e : vars.entrySet()) {
          final String key = e.getKey();
          final String[] val = e.getValue();
          if(val.length == 2) xq.bind(key, val[0], val[1]);
          if(val.length == 1) xq.bind(key, val[0]);
        }

        // initializes the response with query serialization options
        http.sopts().parse(xq.parameters(context));
        http.initResponse();

        // run query
        run(xq, http.res.getOutputStream());
      } else {
        run(cmd, http.res.getOutputStream());
      }
    }
  }