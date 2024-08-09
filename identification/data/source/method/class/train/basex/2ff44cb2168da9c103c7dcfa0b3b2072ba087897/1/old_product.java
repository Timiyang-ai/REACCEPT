private AQuery query(final Function f) {
    final HTTPContext http = session.http;
    return new XQuery(f.args("$d", "$p")).bind("d", http.db()).bind("p", http.dbpath());
  }