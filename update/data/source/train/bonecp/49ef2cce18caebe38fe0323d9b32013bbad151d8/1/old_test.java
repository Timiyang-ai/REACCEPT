@Test
	public void testGetConnection() throws SQLException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {

		Field field = testClass.getClass().getDeclaredField("autocommit");
		field.setAccessible(true);
		field.set(testClass, true);


		field = testClass.getClass().getDeclaredField("isolation");
		field.setAccessible(true);
		field.set(testClass, 0);


		expect(mockPool.getConnection()).andReturn(mockConnection).once();
		expect(mockConnection.getAutoCommit()).andReturn(false).once();
		mockConnection.setTransactionIsolation(0);
		expectLastCall().once();
		replay(mockPool, mockConnection);
		testClass.getConnection();
		verify(mockPool, mockConnection);

	}