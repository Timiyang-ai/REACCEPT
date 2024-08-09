@Test
public void close() {
    // Close an active client connection
    query(conn() + " ! " + _CLIENT_CLOSE.args(" ."));
    
    // Attempt to close a non-existent session, expecting an error indicating the session is not available
    error(_CLIENT_CLOSE.args("xs:anyURI('unknown')"), Err.BXCL_NOTAVL);
    
    // Attempt to close an already closed session, expecting an error indicating the session is not available
    // This tests the idempotency of the close operation and error handling for repeated close attempts
    error(conn() + " ! (" + _CLIENT_CLOSE.args(" .") + ", " +
        _CLIENT_CLOSE.args(" .") + ")", Err.BXCL_NOTAVL);
}