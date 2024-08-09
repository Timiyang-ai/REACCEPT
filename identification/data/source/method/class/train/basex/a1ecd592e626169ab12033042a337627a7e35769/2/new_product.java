static RESTExec get(final RESTSession rs) throws IOException {
    RESTCmd.parseOptions(rs);

    final HTTPContext http = rs.http;
    final String db = http.db();
    if(db.isEmpty()) throw HTTPCode.NO_PATH.get();

    // open database to ensure it exists
    rs.add(new Open(db));
    final String path = http.dbpath();
    if(path.isEmpty()) rs.add(new DropDB(db));
    else rs.add(new Delete(path));

    return new RESTExec(rs);
  }