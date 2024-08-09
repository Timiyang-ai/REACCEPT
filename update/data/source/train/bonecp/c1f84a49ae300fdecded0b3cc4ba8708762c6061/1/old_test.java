@Test
	public void testPutConnectionBackInPartition() throws InterruptedException {
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		AtomicInteger ai = new AtomicInteger(1);
		expect(mockPartition.getAvailableConnections()).andReturn(ai).anyTimes();
		
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		expect(mockConnectionHandles.tryTransfer(mockConnection)).andReturn(false).anyTimes();
		mockConnectionHandles.put(mockConnection);
		expectLastCall().once();
		replay(mockPartition, mockConnectionHandles, mockConnection);
		testClass.putConnectionBackInPartition(mockConnection);
		assertEquals(2, ai.get());
		verify(mockPartition, mockConnectionHandles);
		
	}