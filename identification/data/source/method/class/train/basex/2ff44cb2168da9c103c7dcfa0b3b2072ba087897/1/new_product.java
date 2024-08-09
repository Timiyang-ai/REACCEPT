private AQuery query(final Function f) {
    final HTTPContext http = session.http;
    final String query = "declare variable $d external;" +
        "declare variable $p external;" + f.args("$d", "$p");
    return new XQuery(query).bind("d", http.db()).bind("p", http.dbpath());
  }