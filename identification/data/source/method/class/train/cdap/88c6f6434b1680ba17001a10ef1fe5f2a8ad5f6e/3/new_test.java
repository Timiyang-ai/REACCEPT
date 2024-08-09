  @Test
  public void executeTest() throws Exception {
    List<ColumnDesc> columnDescriptions = Lists.newArrayList(new ColumnDesc("column1", "STRING", 1, ""));
    List<QueryResult> queryResults = Lists.newArrayList();
    ExploreClient exploreClient = new MockExploreClient(
        ImmutableMap.of(
          "mock_query_1", columnDescriptions,
          "mock_query_2", columnDescriptions,
          "mock_query_3", columnDescriptions,
          "mock_query_4", columnDescriptions
        ),
        ImmutableMap.of(
          "mock_query_1", queryResults,
          "mock_query_2", queryResults,
          "mock_query_3", queryResults,
          "mock_query_4", queryResults
          )
    );

    // Make sure an empty query still has a ResultSet associated to it
    ExploreStatement statement = new ExploreStatement(null, exploreClient, "ns1");
    Assert.assertTrue(statement.execute("mock_query_1"));
    ResultSet rs = statement.getResultSet();
    Assert.assertNotNull(rs);
    Assert.assertFalse(rs.isClosed());
    Assert.assertFalse(rs.next());

    rs = statement.executeQuery("mock_query_2");
    Assert.assertNotNull(rs);
    Assert.assertFalse(rs.isClosed());
    Assert.assertFalse(rs.next());

    // Make sure subsequent calls to an execute method close the previous results
    ResultSet rs2 = statement.executeQuery("mock_query_3");
    Assert.assertTrue(rs.isClosed());
    Assert.assertNotNull(rs2);
    Assert.assertFalse(rs2.isClosed());
    Assert.assertFalse(rs2.next());

    Assert.assertTrue(statement.execute("mock_query_4"));
    Assert.assertTrue(rs2.isClosed());
  }