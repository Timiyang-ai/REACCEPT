@Test
	public void testReleaseConnection() throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException, InterruptedException {
		// Test #1: Test releasing connections directly (without helper threads)
		Field field = testClass.getClass().getDeclaredField("releaseHelperThreadsConfigured");
		field.setAccessible(true);
		field.setBoolean(testClass, false);

		expect(mockConnection.isPossiblyBroken()).andReturn(false).once();
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockPartition.getAvailableConnections()).andReturn(1).anyTimes();

		expect(mockConnectionHandles.offer(mockConnection)).andReturn(false).anyTimes();
		//		expect(mockConnectionHandles.offer(mockConnection)).andReturn(true).once();

		// should reset last connection use
		mockConnection.setConnectionLastUsedInMs(anyLong());
		expectLastCall().once();
		Connection mockRealConnection = createNiceMock(Connection.class);
		expect(mockConnection.getInternalConnection()).andReturn(mockRealConnection).anyTimes();
	
		replay(mockRealConnection, mockConnection, mockPartition, mockConnectionHandles);
		testClass.releaseConnection(mockConnection);
		verify(mockConnection, mockPartition, mockConnectionHandles);

		reset(mockConnection, mockPartition, mockConnectionHandles);
	}