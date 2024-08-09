@Override
  public OperationHandle executeStatementAsync(SessionHandle sessionHandle, String statement,
      Map<String, String> confOverlay, long queryTimeout) throws HiveSQLException {
    OperationHandle opHandle =
        sessionManager.getSession(sessionHandle).executeStatementAsync(statement, confOverlay,
            queryTimeout);
    LOG.debug(sessionHandle + ": executeStatementAsync()");
    return opHandle;
  }