@Test
	public void testGetTotalLeased() {
		expect(mockPartition.getCreatedConnections()).andReturn(5).anyTimes();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockPartition.getAvailableConnections()).andReturn(new AtomicInteger(3)).anyTimes();
		replay(mockPartition, mockConnectionHandles);
		assertEquals(4, testClass.getTotalLeased());
		verify(mockPartition, mockConnectionHandles);
		
	}