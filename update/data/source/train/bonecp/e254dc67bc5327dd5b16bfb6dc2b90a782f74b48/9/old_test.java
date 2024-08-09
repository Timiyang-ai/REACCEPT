@Test
	public void testInternalReleaseConnection() throws InterruptedException, SQLException {
		// Test #1: Test normal case where connection is considered to be not broken
		// should reset last connection use
		mockConnection.setConnectionLastUsed(anyLong());
		expectLastCall().once();
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnectionHandles.offer(mockConnection)).andReturn(false).anyTimes();
		mockConnectionHandles.put(mockConnection);
		expectLastCall().once();
		
		replay(mockConnection,mockPartition, mockConnectionHandles);
		testClass.internalReleaseConnection(mockConnection);
		verify(mockConnection,mockPartition, mockConnectionHandles);
		
		// Test #2: Test case where connection is broken
		reset(mockConnection,mockPartition, mockConnectionHandles);
		expect(mockConnection.isPossiblyBroken()).andReturn(true).once();
		expect(mockConfig.getConnectionTestStatement()).andReturn(null).once();
		// make it fail to return false
		expect(mockConnection.getMetaData()).andThrow(new SQLException()).once();

		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		// we're about to destroy this connection, so we can create new ones.
		mockPartition.setUnableToCreateMoreTransactions(false);
		expectLastCall().once();

		// break out from the next method, we're not interested in it
		expect(mockPartition.isUnableToCreateMoreTransactions()).andReturn(true).once();
		replay(mockPartition, mockConnection);
		testClass.internalReleaseConnection(mockConnection);
		verify(mockPartition, mockConnection);
		
	}