@Override
  public OperationHandle executeStatement(SessionHandle sessionHandle, String statement,
      Map<String, String> confOverlay) throws HiveSQLException {
    HiveSession session = sessionManager.getSession(sessionHandle);
    // need to reset the monitor, as operation handle is not available down stream, Ideally the
    // monitor should be associated with the operation handle.
    session.getSessionState().updateProgressMonitor(null);
    OperationHandle opHandle = session.executeStatement(statement, confOverlay);
    LOG.debug(sessionHandle + ": executeStatement()");
    return opHandle;
  }