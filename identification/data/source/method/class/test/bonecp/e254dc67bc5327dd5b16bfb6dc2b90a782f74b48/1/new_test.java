@SuppressWarnings("unchecked")
	@Test
	public void testReleaseConnection() throws SQLException, IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException, InterruptedException {
		// Test #1: Test releasing connections directly (without helper threads)
		Field field = testClass.getClass().getDeclaredField("releaseHelperThreadsConfigured");
		field.setAccessible(true);
		field.setBoolean(testClass, false);

		expect(mockConnection.isPossiblyBroken()).andReturn(false).once();
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockPartition.getAvailableConnections()).andReturn(new AtomicInteger(1)).anyTimes();
		
		expect(mockConnectionHandles.offer(mockConnection)).andReturn(false).anyTimes();
		mockConnectionHandles.put(mockConnection);
		expectLastCall().once();

		// should reset last connection use
		mockConnection.setConnectionLastUsed(anyLong());
		expectLastCall().once();
		replay(mockConnection, mockPartition, mockConnectionHandles);
		testClass.releaseConnection(mockConnection);
		verify(mockConnection, mockPartition, mockConnectionHandles);

		reset(mockConnection, mockPartition, mockConnectionHandles);

		// Test #2: Same test as 1 but throw an exception instead
		field = testClass.getClass().getDeclaredField("releaseHelperThreadsConfigured");
		field.setAccessible(true);
		field.setBoolean(testClass, false);

		/*
		expect(mockConnection.isPossiblyBroken()).andReturn(false).once();
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnectionHandles.offer(mockConnection)).andReturn(false).anyTimes();
		mockConnectionHandles.put(mockConnection);
		expectLastCall().andThrow(new InterruptedException()).once();
 
		// should reset last connection use
		mockConnection.setConnectionLastUsed(anyLong());
		expectLastCall().once();
		replay(mockConnection, mockPartition, mockConnectionHandles);
		try{
			testClass.releaseConnection(mockConnection);
			fail("Should have thrown an exception");
		} catch (SQLException e){ 
			// do nothing
		}
		verify(mockConnection, mockPartition, mockConnectionHandles);
*/
		
		reset(mockConnection, mockPartition, mockConnectionHandles);

		// Test #2: Test with releaser helper threads configured
		field = testClass.getClass().getDeclaredField("releaseHelperThreadsConfigured");
		field.setAccessible(true);
		field.setBoolean(testClass, true);

		LinkedTransferQueue<ConnectionHandle> mockPendingRelease = createNiceMock(LinkedTransferQueue.class);
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		expect(mockPartition.getConnectionsPendingRelease()).andReturn(mockPendingRelease).anyTimes();
		mockPendingRelease.put(mockConnection);
		expectLastCall().once();
		
		replay(mockConnection, mockPartition, mockConnectionHandles, mockPendingRelease);
		testClass.releaseConnection(mockConnection);
		verify(mockConnection, mockPartition, mockConnectionHandles, mockPendingRelease);

	}