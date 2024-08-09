@Test
	public void testPutConnectionBackInPartition() throws InterruptedException {
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		mockConnectionHandles.put(mockConnection);
		expectLastCall().once();
		replay(mockPartition, mockConnectionHandles, mockConnection);
		testClass.putConnectionBackInPartition(mockConnection);
		verify(mockPartition, mockConnectionHandles);
		
	}