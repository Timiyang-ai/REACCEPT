@Test
	public void testReleaseConnection() throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException, InterruptedException {
		// Test #1: Test releasing connections directly
		expect(mockConnection.isPossiblyBroken()).andReturn(false).once();
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockPartition.getAvailableConnections()).andReturn(1).anyTimes();

		//		expect(mockConnectionHandles.offer(mockConnection)).andReturn(true).once();

		// should reset last connection use
		mockConnection.setConnectionLastUsedInMs(anyLong());
		expectLastCall().once();
		Connection mockRealConnection = EasyMock.createNiceMock(Connection.class);
		expect(mockConnection.getInternalConnection()).andReturn(mockRealConnection).anyTimes();
	
		replay(mockRealConnection, mockConnection, mockPartition, mockConnectionHandles);
		testClass.releaseConnection(mockConnection);
		verify(mockConnection, mockPartition, mockConnectionHandles);

		reset(mockConnection, mockPartition, mockConnectionHandles);
	}