@Test
	public void testPutConnectionBackInPartition() throws InterruptedException, SQLException {
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockPartition.getAvailableConnections()).andReturn(1).anyTimes();
		
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		expect(mockConnectionHandles.tryTransfer(mockConnection)).andReturn(false).anyTimes();
		expect(mockConnectionHandles.tryPut(mockConnection)).andReturn(true).once();
		replay(mockPartition, mockConnectionHandles, mockConnection);
		testClass.putConnectionBackInPartition(mockConnection);
		// FIXME
//		assertEquals(2, ai.get());
		verify(mockPartition, mockConnectionHandles);
		
	}