@Test
  public void testReplenishConnections() {
    connectionTracker = new ConnectionTracker(routerConfig.routerScalingUnitMaxConnectionsPerPortPlainText,
        routerConfig.routerScalingUnitMaxConnectionsPerPortSsl);
    // When no connections were ever made to a host:port, connectionTracker should return null, but
    // initiate connections.
    int minActiveConnectionsCount = 0;
    int totalConnectionsCount = 0;
    int availableCount = 0;

    MockDataNodeId dataNodeId1 =
        new MockDataNodeId("host1", Collections.singletonList(plainTextPort), Collections.emptyList(), "DC1");
    MockDataNodeId dataNodeId2 =
        new MockDataNodeId("host2", Arrays.asList(plainTextPort, sslPort), Collections.emptyList(), "DC1");
    dataNodeId2.setSslEnabledDataCenters(Collections.singletonList("DC1"));
    connectionTracker.setMinimumActiveConnectionsPercentage(dataNodeId1, 50);
    minActiveConnectionsCount += 50 * routerConfig.routerScalingUnitMaxConnectionsPerPortPlainText / 100;
    connectionTracker.setMinimumActiveConnectionsPercentage(dataNodeId2, 200);
    minActiveConnectionsCount += routerConfig.routerScalingUnitMaxConnectionsPerPortSsl;

    // call replenishConnections to warm up connections
    assertCounts(totalConnectionsCount, availableCount);
    connectionTracker.replenishConnections(this::mockNewConnection);
    totalConnectionsCount += minActiveConnectionsCount;
    assertCounts(totalConnectionsCount, availableCount);
    List<String> newConnections = getNewlyEstablishedConnections();
    newConnections.forEach(connectionTracker::checkInConnection);
    availableCount += minActiveConnectionsCount;
    assertCounts(totalConnectionsCount, availableCount);
    Assert.assertTrue(connectionTracker.mayCreateNewConnection("host1", plainTextPort, dataNodeId1));
    Assert.assertFalse(connectionTracker.mayCreateNewConnection("host2", sslPort, dataNodeId2));

    // remove 2 connections
    newConnections.stream().limit(2).forEach(connectionTracker::removeConnection);
    totalConnectionsCount -= 2;
    availableCount -= 2;
    assertCounts(totalConnectionsCount, availableCount);

    // replenish connections again
    connectionTracker.replenishConnections(this::mockNewConnection);
    totalConnectionsCount += 2;
    assertCounts(totalConnectionsCount, availableCount);
    newConnections = getNewlyEstablishedConnections();
    newConnections.forEach(connectionTracker::checkInConnection);
    availableCount += 2;
    assertCounts(totalConnectionsCount, availableCount);

    // check out connections
    String conn1 = connectionTracker.checkOutConnection("host1", plainTextPort, dataNodeId1);
    Assert.assertNotNull(conn1);
    String conn2 = connectionTracker.checkOutConnection("host2", sslPort, dataNodeId2);
    Assert.assertNotNull(conn2);
    availableCount -= 2;
    assertCounts(totalConnectionsCount, availableCount);

    // destroy one and return the other and then replenish
    connectionTracker.removeConnection(conn1);
    connectionTracker.checkInConnection(conn2);
    totalConnectionsCount -= 1;
    availableCount += 1;
    assertCounts(totalConnectionsCount, availableCount);
    connectionTracker.replenishConnections(this::mockNewConnection);
    totalConnectionsCount += 1;
    assertCounts(totalConnectionsCount, availableCount);
  }