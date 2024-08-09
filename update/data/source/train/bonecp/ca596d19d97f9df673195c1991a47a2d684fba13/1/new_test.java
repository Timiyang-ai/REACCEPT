@Test
	public void testTerminateAllConnections() throws SQLException {
		expect(mockConnectionHandles.poll()).andReturn(mockConnection).times(2).andReturn(null).once();
		mockConnection.internalClose();
		expectLastCall().once().andThrow(new SQLException()).once();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		replay(mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);
		
		// test.
		testClass.terminateAllConnections();
		verify(mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);

		// same test but to cover the finally section
		reset(mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);
		expect(mockConnectionHandles.poll()).andReturn(mockConnection).anyTimes();
		mockConnection.internalClose();
		expectLastCall().once().andThrow(new RuntimeException()).once();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		replay(mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);
		
		// test.
		try{
			testClass.terminateAllConnections();
			fail("Should throw exception");
		} catch (RuntimeException e){
			// do nothing
		}
		verify(mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);
		
	}