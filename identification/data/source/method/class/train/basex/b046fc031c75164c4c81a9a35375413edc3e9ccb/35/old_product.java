private Uri connect(final QueryContext qc) throws QueryException {
    final String host = Token.string(checkStr(exprs[0], qc));
    final String user = Token.string(checkStr(exprs[2], qc));
    final String pass = Token.string(checkStr(exprs[3], qc));
    final int port = (int) checkItr(exprs[1], qc);
    try {
      return sessions(qc).add(new ClientSession(host, port, user, pass));
    } catch(final IOException ex) {
      throw BXCL_CONN.get(info, ex);
    }
  }