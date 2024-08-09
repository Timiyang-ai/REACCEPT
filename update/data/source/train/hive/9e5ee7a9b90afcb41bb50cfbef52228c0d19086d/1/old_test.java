@Test
  public void testExecuteStatementAsync() throws Exception {
    Map<String, String> confOverlay = new HashMap<String, String>();
    String tableName = "TEST_EXEC_ASYNC";
    String columnDefinitions = "(ID STRING)";
    String queryString;

    // Open a session and set up the test data
    SessionHandle sessionHandle = setupTestData(tableName, columnDefinitions, confOverlay);
    assertNotNull(sessionHandle);

    OperationState state = null;
    OperationHandle opHandle;
    OperationStatus opStatus = null;

    // Change lock manager, otherwise unit-test doesn't go through
    queryString = "SET " + HiveConf.ConfVars.HIVE_SUPPORT_CONCURRENCY.varname
        + " = false";
    opHandle = client.executeStatement(sessionHandle, queryString, confOverlay);
    client.closeOperation(opHandle);

    // Set longPollingTimeout to a custom value for different test cases
    long longPollingTimeout;

    /**
     * Execute a malformed async query with default config,
     * to give a compile time error.
     * (compilation is done synchronous as of now)
     */
    longPollingTimeout = new HiveConf().getLongVar(ConfVars.HIVE_SERVER2_LONG_POLLING_TIMEOUT);
    queryString = "SELECT NON_EXISTING_COLUMN FROM " + tableName;
    try {
      runQueryAsync(sessionHandle, queryString, confOverlay, OperationState.ERROR, longPollingTimeout);
    }
    catch (HiveSQLException e) {
      // expected error
    }

    /**
     * Execute a malformed async query with default config,
     * to give a runtime time error.
     * Also check that the sqlState and errorCode should be set
     */
    queryString = "CREATE TABLE NON_EXISTING_TAB (ID STRING) location 'invalid://localhost:10000/a/b/c'";
    opStatus = runQueryAsync(sessionHandle, queryString, confOverlay, OperationState.ERROR, longPollingTimeout);
    // sqlState, errorCode should be set
    assertEquals(opStatus.getOperationException().getSQLState(), "08S01");
    assertEquals(opStatus.getOperationException().getErrorCode(), 1);
    /**
     * Execute an async query with default config
     */
    queryString = "SELECT ID FROM " + tableName;
    runQueryAsync(sessionHandle, queryString, confOverlay, OperationState.FINISHED, longPollingTimeout);

    /**
     * Execute an async query with long polling timeout set to 0
     */
    longPollingTimeout = 0;
    queryString = "SELECT ID FROM " + tableName;
    runQueryAsync(sessionHandle, queryString, confOverlay, OperationState.FINISHED, longPollingTimeout);

    /**
     * Execute an async query with long polling timeout set to 500 millis
     */
    longPollingTimeout = 500;
    queryString = "SELECT ID FROM " + tableName;
    runQueryAsync(sessionHandle, queryString, confOverlay, OperationState.FINISHED, longPollingTimeout);

    /**
     * Cancellation test
     */
    queryString = "SELECT ID FROM " + tableName;
    opHandle = client.executeStatementAsync(sessionHandle, queryString, confOverlay);
    System.out.println("Cancelling " + opHandle);
    client.cancelOperation(opHandle);
    state = client.getOperationStatus(opHandle).getState();
    System.out.println(opHandle + " after cancelling, state= " + state);
    assertEquals("Query should be cancelled", OperationState.CANCELED, state);

    // Cleanup
    queryString = "DROP TABLE " + tableName;
    client.executeStatement(sessionHandle, queryString, confOverlay);
    client.closeSession(sessionHandle);
  }