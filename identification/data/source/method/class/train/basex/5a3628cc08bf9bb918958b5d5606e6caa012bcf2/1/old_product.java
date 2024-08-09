private XQuery query(final Function f) {
    final HTTPConnection conn = session.conn;
    final String query = "declare variable $d external;" +
        "declare variable $p external;" + f.args("$d", "$p");
    return new XQuery(query).bind("d", conn.db()).bind("p", conn.dbpath());
  }