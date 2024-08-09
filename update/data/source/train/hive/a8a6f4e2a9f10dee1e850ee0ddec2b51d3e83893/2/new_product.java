@Override
  public OperationHandle executeStatementAsync(SessionHandle sessionHandle, String statement,
      Map<String, String> confOverlay, long queryTimeout) throws HiveSQLException {
    HiveSession session = sessionManager.getSession(sessionHandle);
    // need to reset the monitor, as operation handle is not available down stream, Ideally the
    // monitor should be associated with the operation handle.
    session.getSessionState().updateProgressMonitor(null);
    OperationHandle opHandle = session.executeStatementAsync(statement, confOverlay, queryTimeout);
    LOG.debug(sessionHandle + ": executeStatementAsync()");
    return opHandle;
  }