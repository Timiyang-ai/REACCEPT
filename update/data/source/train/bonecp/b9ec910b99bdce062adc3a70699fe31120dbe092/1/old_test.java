@Test
	@Ignore
	public void testTerminateAllConnections2() throws SQLException {
		Connection mockRealConnection = EasyMock.createNiceMock(Connection.class);

		// same test but to cover the finally section
		reset(mockRealConnection, mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);
		expect(mockConnectionHandles.poll()).andReturn(mockConnection).anyTimes();
		expect(mockConnection.getInternalConnection()).andReturn(mockRealConnection).anyTimes();
		mockConnection.internalClose();
		expectLastCall().once().andThrow(new RuntimeException()).once();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		replay(mockRealConnection, mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);

		// test.
		try{
			testClass.connectionStrategy.terminateAllConnections();
			fail("Should throw exception");
		} catch (RuntimeException e){
			// do nothing
		}
		verify(mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);
	}