public static RESTCmd get(final RESTSession session) throws IOException {
    final HTTPConnection conn = session.conn;
    String enc = conn.req.getCharacterEncoding();
    if(enc == null) enc = Strings.UTF8;

    // perform queries
    final byte[] input = new NewlineInput(conn.req.getInputStream()).encoding(enc).content();
    final Context ctx = conn.context;
    final DBNode doc;
    try {
      doc = new DBNode(new IOContent(input));
    } catch(final IOException ex) {
      throw HTTPCode.BAD_REQUEST_X.get(ex);
    }

    try {
      // handle request
      final String cmd = value("local-name(*)", doc, ctx);
      if(cmd.equals(COMMANDS)) {
        final String script = DataBuilder.stripNS(doc, REST_URI, ctx).serialize().toString();
        return RESTScript.get(session, script);
      }

      // handle serialization parameters
      final SerializerOptions sopts = conn.sopts();
      try(QueryProcessor qp = new QueryProcessor("*/*:parameter", ctx).context(doc)) {
        for(final Item param : qp.value()) {
          final String name = value("@name", param, ctx);
          final String value = value("@value", param, ctx);
          if(sopts.option(name) != null) {
            sopts.assign(name, value);
          } else {
            throw HTTPCode.UNKNOWN_PARAM_X.get(name);
          }
        }
      }

      // handle database options
      try(QueryProcessor qp = new QueryProcessor("*/*:option", ctx).context(doc)) {
        for(final Item item : qp.value()) {
          final String name = value("@name", item, ctx).toUpperCase(Locale.ENGLISH);
          final String value = value("@value", item, ctx);
          ctx.options.assign(name, value);
        }
      }

      // handle variables
      final Map<String, String[]> vars = new HashMap<>();
      try(QueryProcessor qp = new QueryProcessor("*/*:variable", ctx).context(doc)) {
        for(final Item item : qp.value()) {
          final String name = value("@name", item, ctx);
          final String value = value("@value", item, ctx);
          final String type = value("@type", item, ctx);
          vars.put(name, new String[] { value, type });
        }
      }

      // handle input
      String val = null;
      try(QueryProcessor qp = new QueryProcessor("*/*:context/(*, text()[normalize-space()])",
          ctx).context(doc)) {
        for(final Item item : qp.value()) {
          if(val != null) throw HTTPCode.MULTIPLE_CONTEXT_X.get();
          // create main memory instance of the specified node
          val = DataBuilder.stripNS((ANode) item, REST_URI, ctx).serialize().toString();
        }
      }

      // command body
      final String text = value("*/*:text/text()", doc, ctx);

      // choose evaluation
      if(cmd.equals(COMMAND)) return RESTCommand.get(session, text);
      if(cmd.equals(RUN)) return RESTRun.get(session, text, vars, val);
      if(cmd.equals(QUERY)) return RESTQuery.get(session, text, vars, val);
      throw HTTPCode.BAD_REQUEST_X.get("Invalid POST command: " + cmd);

    } catch(final QueryException ex) {
      throw HTTPCode.BAD_REQUEST_X.get(ex);
    }
  }