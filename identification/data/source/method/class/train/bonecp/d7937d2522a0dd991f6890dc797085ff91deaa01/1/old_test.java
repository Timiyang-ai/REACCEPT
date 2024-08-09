@Test
	public void testIsConnectionHandleAlive() throws SQLException {
		// Test 1: Normal case (+ without connection test statement)
		expect(mockConfig.getConnectionTestStatement()).andReturn(null).once();
		expect(mockConnection.getMetaData()).andReturn(mockDatabaseMetadata).once();
		expect(mockDatabaseMetadata.getTables((String)anyObject(), (String)anyObject(), (String)anyObject(), (String[])anyObject())).andReturn(mockResultSet).once();
		mockResultSet.close();
		expectLastCall().once();

		replay(mockConfig, mockConnection, mockDatabaseMetadata, mockResultSet);
		assertTrue(testClass.isConnectionHandleAlive(mockConnection));
		verify(mockConfig, mockConnection, mockResultSet,mockDatabaseMetadata);
	}