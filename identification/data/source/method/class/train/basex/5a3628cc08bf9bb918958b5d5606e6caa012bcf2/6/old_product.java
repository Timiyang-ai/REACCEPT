void retrieve(final String db, final String path, final boolean raw, final OutputStream out)
      throws IOException {

    session().setOutputStream(out);
    final String string = SerializerOptions.USE_CHARACTER_MAPS.arg(WEBDAV) +
        (raw ? _DB_RETRIEVE : _DB_OPEN).args("$db", "$path") + "[1]";
    final WebDAVQuery query = new WebDAVQuery(string);
    query.bind("db", db);
    query.bind("path", path);
    execute(query);
  }