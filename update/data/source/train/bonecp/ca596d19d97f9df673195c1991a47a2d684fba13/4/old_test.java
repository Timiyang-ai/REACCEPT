@SuppressWarnings("unchecked")
	@Test
	public void clearResultSetHandles() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, SQLException{
		Statement mockStatement = createNiceMock(Statement.class);
		ResultSet mockResultSet = createNiceMock(ResultSet.class);
		ConcurrentLinkedQueue<ResultSet> mockResultSetHandles = createNiceMock(ConcurrentLinkedQueue.class);

		// alternate constructor
		StatementHandle handle = new StatementHandle(mockStatement, null);
		Field field = handle.getClass().getDeclaredField("resultSetHandles");
		field.setAccessible(true);
		field.set(handle, mockResultSetHandles);
		expect(mockResultSetHandles.poll()).andReturn(mockResultSet).once().andReturn(null).once();
		mockResultSet.close();
		expectLastCall().once();
		replay(mockResultSetHandles, mockResultSet, mockStatement);
		handle.clearResultSetHandles(true);
		verify(mockResultSet, mockResultSetHandles, mockStatement);
		
	}