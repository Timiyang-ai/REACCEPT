static void open(final RESTSession rs) {
    final String db = rs.http.db();
    if(db != null) rs.add(new Open(db, rs.http.dbpath()));
  }