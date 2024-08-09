public static RESTExec get(final RESTSession rs) throws IOException {
    // create new database or update resource
    final HTTPContext http = rs.http;
    if(http.depth() == 0) throw HTTPCode.NO_PATH.get();

    RESTCmd.parseOptions(rs);

    boolean xml = true;
    final InputStream is = http.req.getInputStream();
    final String ct = http.contentType();
    // choose correct importer
    MainParser parser = null;
    if(isJSON(ct)) {
      parser = MainParser.JSON;
      if(APP_JSONML.equals(ct)) {
        final JsonParserOptions jopts = new JsonParserOptions();
        jopts.set(JsonOptions.FORMAT, JsonFormat.JSONML);
        rs.context.options.set(MainOptions.JSONPARSER, jopts);
      }
    } else if(TEXT_CSV.equals(ct)) {
      parser = MainParser.CSV;
    } else if(TEXT_HTML.equals(ct)) {
      parser = MainParser.HTML;
    } else if(ct != null && isText(ct)) {
      parser = MainParser.TEXT;
    } else if(ct != null && !isXML(ct)) {
      xml = false;
    }
    if(parser != null) rs.context.options.set(MainOptions.PARSER, parser);

    // store data as XML or raw file, depending on content type
    final String db = http.db();
    if(http.depth() == 1) {
      if(xml) {
        rs.add(new CreateDB(db), is);
      } else {
        rs.add(new CreateDB(db));
        rs.add(new Store(db), is);
      }
    } else {
      rs.add(new Open(db));
      final String path = http.dbpath();
      if(xml) {
        rs.add(new Replace(path), is);
      } else {
        rs.add(new Delete(path));
        rs.add(new Store(path), is);
      }
    }
    final RESTExec cmd = new RESTExec(rs);
    cmd.code = HTTPCode.CREATED_X;
    return cmd;
  }