@Test
	public void testMaybeSignalForMoreConnections() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		expect(mockPartition.isUnableToCreateMoreTransactions()).andReturn(false).once();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
//		expect(mockConnectionHandles.size()).andReturn(1).anyTimes();
		expect(mockPartition.getAvailableConnections()).andReturn(new AtomicInteger(1)).anyTimes();
		expect(mockPartition.getMaxConnections()).andReturn(10).anyTimes();
		mockPartition.lockAlmostFullLock();
		expectLastCall().once();
		mockPartition.almostFullSignal();
		expectLastCall().once();
		mockPartition.unlockAlmostFullLock();
		expectLastCall().once();
		replay(mockPartition, mockConnectionHandles);
		Method method = testClass.getClass().getDeclaredMethod("maybeSignalForMoreConnections", ConnectionPartition.class);
		method.setAccessible(true);
		method.invoke(testClass, new Object[]{mockPartition});
		verify(mockPartition, mockConnectionHandles);
		
		// Test 2, same test but fake an exception
		reset(mockPartition, mockConnectionHandles);
		expect(mockPartition.isUnableToCreateMoreTransactions()).andReturn(false).anyTimes();
		expect(mockPartition.getFreeConnections()).andReturn(mockConnectionHandles).anyTimes();
		expect(mockConnectionHandles.size()).andReturn(1).anyTimes();
		expect(mockPartition.getMaxConnections()).andReturn(10).anyTimes();
		expect(mockPartition.getAvailableConnections()).andReturn(new AtomicInteger(1)).anyTimes();

		mockPartition.lockAlmostFullLock();
		expectLastCall().once();
		mockPartition.almostFullSignal();
		expectLastCall().andThrow(new RuntimeException()).once();
		mockPartition.unlockAlmostFullLock();
		expectLastCall().once(); 
		replay(mockPartition, mockConnectionHandles);
		try{
			method.invoke(testClass, new Object[]{mockPartition});
			fail("Should have thrown an exception");
		} catch (Throwable t){
			// do nothing
		}
		verify(mockPartition, mockConnectionHandles);
		
	}