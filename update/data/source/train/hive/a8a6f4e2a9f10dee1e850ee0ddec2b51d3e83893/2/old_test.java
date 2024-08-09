@Test
  public void testExecuteStatementAsync() throws Exception {
    Map<String, String> opConf = new HashMap<String, String>();
    // Open a new client session
    SessionHandle sessHandle = client.openSession(USERNAME,
        PASSWORD, opConf);
    // Session handle should not be null
    assertNotNull("Session handle should not be null", sessHandle);

    OperationHandle opHandle;
    OperationStatus opStatus;
    OperationState state = null;

    // Change lock manager to embedded mode
    String queryString = "SET hive.lock.manager=" +
        "org.apache.hadoop.hive.ql.lockmgr.EmbeddedLockManager";
    client.executeStatement(sessHandle, queryString, opConf);

    // Drop the table if it exists
    queryString = "DROP TABLE IF EXISTS TEST_EXEC_ASYNC_THRIFT";
    client.executeStatement(sessHandle, queryString, opConf);

    // Create a test table
    queryString = "CREATE TABLE TEST_EXEC_ASYNC_THRIFT(ID STRING)";
    client.executeStatement(sessHandle, queryString, opConf);

    // Execute another query
    queryString = "SELECT ID+1 FROM TEST_EXEC_ASYNC_THRIFT";
    System.out.println("Will attempt to execute: " + queryString);
    opHandle = client.executeStatementAsync(sessHandle, queryString, opConf);
    assertNotNull(opHandle);

    // Poll on the operation status till the query is completed
    boolean isQueryRunning = true;
    long pollTimeout = System.currentTimeMillis() + 100000;

    while(isQueryRunning) {
      // Break if polling times out
      if (System.currentTimeMillis() > pollTimeout) {
        System.out.println("Polling timed out");
        break;
      }
      opStatus = client.getOperationStatus(opHandle);
      assertNotNull(opStatus);
      state = opStatus.getState();
      System.out.println("Current state: " + state);

      if (state == OperationState.CANCELED ||
          state == OperationState.CLOSED ||
          state == OperationState.FINISHED ||
          state == OperationState.ERROR) {
        isQueryRunning = false;
      }
      Thread.sleep(1000);
    }

    // Expect query to be successfully completed now
    assertEquals("Query should be finished",  OperationState.FINISHED, state);

    // Execute a malformed query
    // This query will give a runtime error
    queryString = "CREATE TABLE NON_EXISTING_TAB (ID STRING) location 'hdfs://localhost:10000/a/b/c'";
    System.out.println("Will attempt to execute: " + queryString);
    opHandle = client.executeStatementAsync(sessHandle, queryString, opConf);
    assertNotNull(opHandle);
    opStatus = client.getOperationStatus(opHandle);
    assertNotNull(opStatus);
    isQueryRunning = true;
    pollTimeout = System.currentTimeMillis() + 100000;
    while(isQueryRunning) {
      // Break if polling times out
      if (System.currentTimeMillis() > pollTimeout) {
        System.out.println("Polling timed out");
        break;
      }
      state = opStatus.getState();
      System.out.println("Current state: " + state);
      if (state == OperationState.CANCELED ||
          state == OperationState.CLOSED ||
          state == OperationState.FINISHED ||
          state == OperationState.ERROR) {
        isQueryRunning = false;
      }
      Thread.sleep(1000);
      opStatus = client.getOperationStatus(opHandle);
    }
    // Expect query to return an error state
    assertEquals("Operation should be in error state",
        OperationState.ERROR, state);
    // sqlState, errorCode should be set to appropriate values
    assertEquals(opStatus.getOperationException().getSQLState(), "08S01");
    assertEquals(opStatus.getOperationException().getErrorCode(), 1);

    // Cleanup
    queryString = "DROP TABLE TEST_EXEC_ASYNC_THRIFT";
    client.executeStatement(sessHandle, queryString, opConf);

    client.closeSession(sessHandle);
  }