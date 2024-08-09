@Test
	public void testTerminateAllConnections() throws SQLException {
		expect(mockConnectionHandles.poll()).andReturn(mockConnection).times(2).andReturn(null).once();
		mockConnection.internalClose();
		expectLastCall().once().andThrow(new SQLException()).once();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		
		replay(mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);
		
		// test.
		testClass.terminateAllConnections();
		verify(mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);

	}