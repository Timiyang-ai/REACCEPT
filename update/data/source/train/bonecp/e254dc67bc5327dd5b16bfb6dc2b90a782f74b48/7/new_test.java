@Test
	public void testGetTotalFree() {
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockPartition.getAvailableConnections()).andReturn(new AtomicInteger(3)).anyTimes();
		
		// expect(mockConnectionHandles.size()).andReturn(3).anyTimes();
		replay(mockPartition, mockConnectionHandles);
		assertEquals(6, testClass.getTotalFree());
		verify(mockPartition, mockConnectionHandles);

	}