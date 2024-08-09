@Test
	public void testPostDestroyConnection(){
		Connection mockRealConnection = createNiceMock(Connection.class);

		reset(mockConnection);
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		mockPartition.updateCreatedConnections(-1);
		expectLastCall().once();
		mockPartition.setUnableToCreateMoreTransactions(false);
		expectLastCall().once();
		ConnectionHook mockConnectionHook = createNiceMock(ConnectionHook.class);
		expect(mockConnection.getConnectionHook()).andReturn(mockConnectionHook).anyTimes();
		expect(mockConnection.getInternalConnection()).andReturn(mockRealConnection).anyTimes();

		mockConnectionHook.onDestroy(mockConnection);
		expectLastCall().once();
		replay(mockRealConnection, mockConnectionHook, mockConnection);
		testClass.postDestroyConnection(mockConnection);
		verify(mockConnectionHook, mockConnection);
	}