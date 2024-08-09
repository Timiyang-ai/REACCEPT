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
	
		// Test #2: get connection, not finding any available block to wait for one
		reset(mockPartition, mockConnectionHandles, mockConnection);
		expect(mockPartition.isUnableToCreateMoreTransactions()).andReturn(true).once();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnectionHandles.poll()).andReturn(null).once();
		expect(mockConnectionHandles.take()).andReturn(mockConnection).once();
		
		mockConnection.setOriginatingPartition(mockPartition);
		expectLastCall().once();
		mockConnection.renewConnection();
		expectLastCall().once();

		replay(mockPartition, mockConnectionHandles, mockConnection);
		assertEquals(mockConnection, testClass.getConnection());
		verify(mockPartition, mockConnectionHandles, mockConnection);
	

		// Test #3: Like test #2 but simulate an interrupted exception
		Field field = testClass.getClass().getDeclaredField("connectionStarvationTriggered");
		field.setAccessible(true);
		field.setBoolean(testClass, true);
		reset(mockPartition, mockConnectionHandles, mockConnection);
		expect(mockPartition.isUnableToCreateMoreTransactions()).andReturn(true).once();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnectionHandles.take()).andThrow(new InterruptedException()).once();
		
		replay(mockPartition, mockConnectionHandles, mockConnection);
		try{
			testClass.getConnection();
			fail("Should have throw an SQL Exception");
		} catch (SQLException e){
			// do nothing
		}
		verify(mockPartition, mockConnectionHandles, mockConnection);

		
		// Test #4: Like test #2 but simulate an interrupted exception in another take()
		 field = testClass.getClass().getDeclaredField("connectionStarvationTriggered");
		field.setAccessible(true);
		field.setBoolean(testClass, false);
		reset(mockPartition, mockConnectionHandles, mockConnection);
		expect(mockPartition.isUnableToCreateMoreTransactions()).andReturn(true).once();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnectionHandles.poll()).andReturn(null).once();
		expect(mockConnectionHandles.take()).andThrow(new InterruptedException()).once();
		
		replay(mockPartition, mockConnectionHandles, mockConnection);
		try{
			testClass.getConnection();
			fail("Should have throw an SQL Exception");
		} catch (SQLException e){
			// do nothing
		}
		verify(mockPartition, mockConnectionHandles, mockConnection);

		// Test #5: Connection queues are starved of free connections. Should block and wait on one without spin-locking.
		field = testClass.getClass().getDeclaredField("connectionStarvationTriggered");
		field.setAccessible(true);
		field.setBoolean(testClass, true);
		reset(mockPartition, mockConnectionHandles, mockConnection);
		expect(mockPartition.isUnableToCreateMoreTransactions()).andReturn(true).once();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnectionHandles.take()).andReturn(mockConnection).once();

		mockConnection.setOriginatingPartition(mockPartition);
		expectLastCall().once();
		mockConnection.renewConnection();
		expectLastCall().once();

		replay(mockPartition, mockConnectionHandles, mockConnection);
		assertEquals(mockConnection, testClass.getConnection());
		verify(mockPartition, mockConnectionHandles, mockConnection);

		// Test #6: If we hit our limit, we should signal for more connections to be created on the fly
		reset(mockPartition, mockConnectionHandles, mockConnection);
		expect(mockPartition.isUnableToCreateMoreTransactions()).andReturn(false).anyTimes();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockPartition.getMaxConnections()).andReturn(1).anyTimes();

		mockPartition.almostFullSignal();
		expectLastCall().once();

		expect(mockConnectionHandles.poll()).andReturn(mockConnection).once();
		mockConnection.setOriginatingPartition(mockPartition);
		expectLastCall().once();
		mockConnection.renewConnection();
		expectLastCall().once();

		replay(mockPartition, mockConnectionHandles, mockConnection);
		testClass.getConnection();
		verify(mockPartition, mockConnectionHandles, mockConnection);

		
		// Test #7: Like test 6, except we fake an unchecked exception to make sure our locks are released.
		field = testClass.getClass().getDeclaredField("connectionsObtainedLock");
		field.setAccessible(true);
		field.set(testClass, mockLock);
		
		field = testClass.getClass().getDeclaredField("connectionsObtained");
		field.setAccessible(true);
		field.setInt(testClass, 0);
	
		reset(mockPartition, mockConnectionHandles, mockConnection);
		expect(mockPartition.isUnableToCreateMoreTransactions()).andReturn(false).anyTimes();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockPartition.getMaxConnections()).andReturn(0).anyTimes(); // cause a division by zero error
		mockLock.lock();
		expectLastCall().once();
		mockLock.unlock();
		expectLastCall().once();
		replay(mockPartition, mockConnectionHandles, mockConnection, mockLock);
		try{
			testClass.getConnection();
			fail("Should have thrown an exception");
		} catch (Throwable t){
			// do nothing
		}
		verify(mockPartition, mockConnectionHandles, mockConnection, mockLock);

		

	}