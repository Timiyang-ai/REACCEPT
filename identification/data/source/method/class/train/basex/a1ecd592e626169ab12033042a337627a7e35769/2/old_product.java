static RESTExec get(final RESTSession rs) throws IOException {
    RESTCmd.parseOptions(rs);

    final HTTPContext http = rs.http;
    if(http.depth() == 0) throw HTTPCode.NO_PATH.get();

    // open database to ensure it exists
    rs.add(new Open(http.db()));
    if(http.depth() == 1) rs.add(new DropDB(http.db()));
    else rs.add(new Delete(http.dbpath()));

    return new RESTExec(rs);
  }