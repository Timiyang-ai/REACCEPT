@Test
	public void testPutConnectionBackInPartition() throws InterruptedException, SQLException {
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockPartition.getAvailableConnections()).andReturn(1).anyTimes();
		Connection mockRealConnection = EasyMock.createNiceMock(Connection.class);
		expect(mockConnection.getInternalConnection()).andReturn(mockRealConnection).anyTimes();
	
		expect(mockConnection.getOriginatingPartition()).andReturn(mockPartition).anyTimes();
		expect(mockConnectionHandles.offer(mockConnection)).andReturn(true).once();
		replay(mockRealConnection, mockPartition, mockConnectionHandles, mockConnection);
		testClass.putConnectionBackInPartition(mockConnection);
		// FIXME
		//		assertEquals(2, ai.get());
		verify(mockPartition, mockConnectionHandles);

	}