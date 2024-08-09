static void open(final RESTSession rs) {
    final String db = rs.http.db();
    if(db == null) return;
    final String dbpath = rs.http.dbpath();

    rs.add(new Open(db));
    if(!dbpath.isEmpty()) rs.add(new Cs(_DB_OPEN.args(db, dbpath)));
  }