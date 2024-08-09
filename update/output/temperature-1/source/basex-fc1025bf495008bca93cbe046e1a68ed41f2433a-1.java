@Test
public void close() {
    // Attempt to close a current session
    query(conn() + " ! " + _CLIENT_CLOSE.args(" ."));
    
    // Error when trying to close a session based on an unknown URI
    // BXCL0002: session not available
    error(_CLIENT_CLOSE.args("xs:anyURI('unknown')"), Err.BXCL_NOTAVL);
    
    // Error when trying to close an already closed session
    // BXCL0002: session has already been closed
    error(conn() + " ! (" + _CLIENT_CLOSE.args(" .") + ", " +
          _CLIENT_CLOSE.args(" .") + ")", Err.BXCL_NOTAVL);
}