@Test
  public void testExecuteStatement() throws Exception {
    Map<String, String> opConf = new HashMap<String, String>();
    // Open a new client session
    SessionHandle sessHandle = client.openSession(USERNAME,
        PASSWORD, opConf);
    // Session handle should not be null
    assertNotNull("Session handle should not be null", sessHandle);

    // Change lock manager to embedded mode
    String queryString = "SET hive.lock.manager=" +
        "org.apache.hadoop.hive.ql.lockmgr.EmbeddedLockManager";
    client.executeStatement(sessHandle, queryString, opConf);

    // Drop the table if it exists
    queryString = "DROP TABLE IF EXISTS TEST_EXEC_THRIFT";
    client.executeStatement(sessHandle, queryString, opConf);

    // Create a test table
    queryString = "CREATE TABLE TEST_EXEC_THRIFT(ID STRING)";
    client.executeStatement(sessHandle, queryString, opConf);

    // Execute another query
    queryString = "SELECT ID+1 FROM TEST_EXEC_THRIFT";
    OperationHandle opHandle = client.executeStatement(sessHandle, queryString, opConf);
    assertNotNull(opHandle);

    OperationStatus opStatus = client.getOperationStatus(opHandle, false);
    assertNotNull(opStatus);

    OperationState state = opStatus.getState();
    // Expect query to be completed now
    assertEquals("Query should be finished", OperationState.FINISHED, state);

    // Cleanup
    queryString = "DROP TABLE TEST_EXEC_THRIFT";
    client.executeStatement(sessHandle, queryString, opConf);

    client.closeSession(sessHandle);
  }