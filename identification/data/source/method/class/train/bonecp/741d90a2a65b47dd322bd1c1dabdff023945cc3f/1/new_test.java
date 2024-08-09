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
		
		// Test 2: Same test as 1 but triggers an exception
		reset(mockConfig, mockConnection, mockDatabaseMetadata, mockResultSet);
		expect(mockConfig.getConnectionTestStatement()).andReturn(null).once();
		expect(mockConnection.getMetaData()).andThrow(new SQLException()).once();
		
		replay(mockConfig, mockConnection, mockDatabaseMetadata, mockResultSet);
		assertFalse(testClass.isConnectionHandleAlive(mockConnection));
		verify(mockConfig, mockConnection, mockResultSet,mockDatabaseMetadata);
		

		// Test 3: Normal case (+ with connection test statement)
		reset(mockConfig, mockConnection, mockDatabaseMetadata, mockResultSet);

		Statement mockStatement = createNiceMock(Statement.class);
		expect(mockConfig.getConnectionTestStatement()).andReturn("whatever").once();
		expect(mockConnection.createStatement()).andReturn(mockStatement).once();
		expect(mockStatement.execute((String)anyObject())).andReturn(true).once();
//		mockResultSet.close();
//		expectLastCall().once();
		
		replay(mockConfig, mockConnection, mockDatabaseMetadata,mockStatement);
		assertTrue(testClass.isConnectionHandleAlive(mockConnection));
		verify(mockConfig, mockConnection,mockDatabaseMetadata, mockStatement);

		// Test 4: Same like test 3 but triggers exception
		reset(mockConfig, mockConnection, mockDatabaseMetadata, mockResultSet, mockStatement);

		expect(mockConfig.getConnectionTestStatement()).andReturn("whatever").once();
		expect(mockConnection.createStatement()).andReturn(mockStatement).once();
		expect(mockStatement.execute((String)anyObject())).andThrow(new RuntimeException()).once();
		mockStatement.close();
		expectLastCall().once();
		
		replay(mockConfig, mockConnection, mockDatabaseMetadata,mockStatement, mockResultSet);
//		assertFalse(testClass.isConnectionHandleAlive(mockConnection));
		try{
			mockConnection.logicallyClosed=true;// (code coverage)
			testClass.isConnectionHandleAlive(mockConnection);
			fail("Should have thrown an exception");
		} catch (RuntimeException e){
			// do nothing 
		}
		verify(mockConfig, mockConnection, mockResultSet,mockDatabaseMetadata, mockStatement);

		// Test 5: Same like test 4 but triggers exception in finally block
		reset(mockConfig, mockConnection, mockDatabaseMetadata, mockResultSet, mockStatement);

		expect(mockConfig.getConnectionTestStatement()).andReturn("whatever").once();
		expect(mockConnection.createStatement()).andReturn(mockStatement).once();
		expect(mockStatement.execute((String)anyObject())).andThrow(new RuntimeException()).once();
		mockStatement.close();
		expectLastCall().andThrow(new SQLException()).once();
		
		replay(mockConfig, mockConnection, mockDatabaseMetadata,mockStatement, mockResultSet);
		try{
			testClass.isConnectionHandleAlive(mockConnection);
			fail("Should have thrown an exception");
		} catch (RuntimeException e){
			// do nothing
		}
		verify(mockConfig, mockConnection, mockResultSet,mockDatabaseMetadata, mockStatement);

	}