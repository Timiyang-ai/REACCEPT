void query(final String path) throws IOException {
    int c = 0;
    while(!(cmds.get(c) instanceof XQuery)) run(cmds.get(c++));
    final XQuery xq = (XQuery) cmds.get(c);

    // create query instance
    if(value != null) xq.bind(null, value, NodeType.DOC.toString());

    // set base path and serialization parameters
    final HTTPContext http = session.http;
    context.options.set(MainOptions.QUERYPATH, path);
    context.options.set(MainOptions.SERIALIZER, serial(http));

    // bind HTTP context and external variables
    xq.http(http);
    for(final Entry<String, String[]> e : variables.entrySet()) {
      final String key = e.getKey();
      final String[] val = e.getValue();
      if(val.length == 2) xq.bind(key, val[0], val[1]);
      if(val.length == 1) xq.bind(key, val[0]);
    }

    // initializes the response with query serialization options
    http.serialization.parse(xq.parameters(context).toString());
    http.initResponse();
    // run query
    run(xq, http.res.getOutputStream());
  }