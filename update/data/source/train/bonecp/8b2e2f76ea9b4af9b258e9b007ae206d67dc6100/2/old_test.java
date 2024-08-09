@Test
	public void testGetConnection() throws SQLException, InterruptedException, IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {
		// Test 1: Get connection - normal state
		
		expect(mockPartition.isUnableToCreateMoreTransactions()).andReturn(true).once();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnectionHandles.poll()).andReturn(mockConnection).once();
		mockConnection.setOriginatingPartition(mockPartition);
		expectLastCall().once();
		mockConnection.renewConnection();
		expectLastCall().once();

		replay(mockPartition, mockConnectionHandles, mockConnection);
		assertEquals(mockConnection, testClass.getConnection());
		verify(mockPartition, mockConnectionHandles, mockConnection);
	}