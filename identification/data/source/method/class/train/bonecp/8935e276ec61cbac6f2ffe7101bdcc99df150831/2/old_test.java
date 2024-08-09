@SuppressWarnings("unchecked")
	@Test 
	public void testInternalClose() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException{
		ConcurrentLinkedQueue<Statement> mockStatementHandles = createNiceMock(ConcurrentLinkedQueue.class);
		StatementHandle mockStatement = createNiceMock(StatementHandle.class);
		Field field = testClass.getClass().getDeclaredField("statementHandles");

		field.setAccessible(true);
		field.set(testClass, mockStatementHandles); 

		expect(mockStatementHandles.poll()).andReturn(mockStatement).once().andReturn(null).once();
		mockStatement.internalClose();
		expectLastCall().once();
		mockConnection.close();
		expectLastCall().once().andThrow(new SQLException()).once();
		replay(mockStatement, mockConnection, mockStatementHandles);
		testClass.internalClose();
		try{
			testClass.internalClose(); //2nd time should throw exception
			fail("Should have thrown an exception");
		} catch (Throwable t){
			// do nothing.
		}

		verify(mockStatement, mockConnection, mockStatementHandles);
	}