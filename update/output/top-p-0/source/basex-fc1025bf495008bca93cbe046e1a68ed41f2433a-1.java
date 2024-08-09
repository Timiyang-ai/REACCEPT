@Test
public void close() {
  query(conn() + " ! " + _CLIENT_CLOSE.args(" ."));
  // BXCL0002: session not available
  error(_CLIENT_CLOSE.args("xs:anyURI('unknown')"), Err.BXCL_NOTAVL);
  // BXCL0002: session has already been closed
  error(conn() + " ! (" + _CLIENT_CLOSE.args(" .") + ", " +
      _CLIENT_CLOSE.args(" .") + ")", Err.BXCL_NOTAVL);
}