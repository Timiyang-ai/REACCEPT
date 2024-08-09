@Test
	public void testGetConnection() throws SQLException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

		Field field = testClass.getClass().getDeclaredField("autocommit");
		field.setAccessible(true);
		field.set(testClass, true);


		field = testClass.getClass().getDeclaredField("isolation");
		field.setAccessible(true);
		field.set(testClass, 8);


		expect(mockPool.getConnection()).andReturn(mockConnection).once();
		expect(mockConnection.getAutoCommit()).andReturn(false).once();
		expect(mockConnection.getTransactionIsolation()).andReturn(0).once();
		mockConnection.setTransactionIsolation(8);
		expectLastCall().once();
		replay(mockPool, mockConnection);
		testClass.getConnection();
		verify(mockPool, mockConnection);

	}