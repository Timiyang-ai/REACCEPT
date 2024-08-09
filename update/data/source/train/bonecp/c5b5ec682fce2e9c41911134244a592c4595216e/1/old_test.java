@Test
	public void testReleaseInAnyFreePartition() throws InterruptedException {
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		// Test 1: Active partition is full up
		expect(mockConnectionHandles.offer(mockConnection)).andReturn(false).once().andReturn(true).once();
		replay(mockPartition, mockConnectionHandles);
		testClass.releaseInAnyFreePartition(mockConnection, mockPartition);
		verify(mockPartition, mockConnectionHandles);
		
		// Test #2: Same test where all partitions are full
		reset(mockPartition, mockConnectionHandles);
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnectionHandles.offer(mockConnection)).andReturn(false).anyTimes();
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).once();
		
		mockConnectionHandles.put(mockConnection);
		expectLastCall().once();
		replay(mockPartition, mockConnectionHandles, mockConnection);
		testClass.releaseInAnyFreePartition(mockConnection, mockPartition);
		verify(mockPartition, mockConnectionHandles, mockConnection);
		
	}