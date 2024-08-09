  private SessionHandle openSession(Map<String, String> confOverlay)
      throws HiveSQLException {
    SessionHandle sessionHandle = client.openSession("tom", "password", confOverlay);
    assertNotNull(sessionHandle);
    SessionState.get().setIsHiveServerQuery(true); // Pretend we are in HS2.

    String queryString = "SET " + HiveConf.ConfVars.HIVE_SUPPORT_CONCURRENCY.varname
      + " = false";
    client.executeStatement(sessionHandle, queryString, confOverlay);
    return sessionHandle;
  }