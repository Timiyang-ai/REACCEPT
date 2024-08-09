public SessionHandle openSession(TProtocolVersion protocol, String username, String password,
      Map<String, String> configuration) throws HiveSQLException {
    SessionHandle sessionHandle = sessionManager.openSession(protocol, username, password, configuration, false, null);
    LOG.debug(sessionHandle + ": openSession()");
    return sessionHandle;
  }