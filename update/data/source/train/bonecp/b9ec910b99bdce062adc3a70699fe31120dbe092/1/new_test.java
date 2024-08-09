@Test
	public void testTerminateAllConnections2() throws SQLException {
		Connection mockRealConnection = EasyMock.createNiceMock(Connection.class);

		// same test but to cover the finally section
		reset(mockRealConnection, mockConnectionsScheduler, mockKeepAliveScheduler, mockPartition, mockConnectionHandles, mockConnection);
		expect(mockPartition.getFreeConnections()).andThrow(new RuntimeException()).once();
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