@Override
  public SessionHandle openSession(String username, String password, Map<String, String> configuration)
      throws HiveSQLException {
    SessionHandle sessionHandle = sessionManager.openSession(SERVER_VERSION, username, password, configuration, false, null);
    LOG.info(sessionHandle + ": openSession()");
    return sessionHandle;
  }