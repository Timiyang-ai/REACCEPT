@Test
	public void testPostDestroyConnection(){
		reset(mockConnection);
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		mockPartition.updateCreatedConnections(-1);
		expectLastCall().once();
		mockPartition.setUnableToCreateMoreTransactions(false);
		expectLastCall().once();
		ConnectionHook mockConnectionHook = createNiceMock(ConnectionHook.class);
		expect(mockConnection.getConnectionHook()).andReturn(mockConnectionHook).anyTimes();
		mockConnectionHook.onDestroy(mockConnection);
		expectLastCall().once();
		replay(mockConnectionHook, mockConnection);
		testClass.postDestroyConnection(mockConnection);
		verify(mockConnectionHook, mockConnection);
	}