private boolean exists(final String db, final String path) throws IOException {
    final WebDAVQuery query = new WebDAVQuery(_DB_EXISTS.args("$db", "$path"));
    query.bind("db", db);
    query.bind("path", path);
    return execute(query).equals(Text.TRUE);
  }