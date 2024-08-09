void retrieve(final String db, final String path, final boolean raw, final OutputStream out)
      throws IOException {

    session().setOutputStream(out);
    final WebDAVQuery query = new WebDAVQuery(
      "declare option output:" + (raw ?
      "method 'raw'; " + _DB_RETRIEVE.args("$db", "$path") + "[1]" :
      "use-character-maps '&#xA0;=&amp;#xA0;'; " + _DB_OPEN.args("$db", "$path")) + "[1]");
    query.bind("db", db);
    query.bind("path", path);
    execute(query);
  }