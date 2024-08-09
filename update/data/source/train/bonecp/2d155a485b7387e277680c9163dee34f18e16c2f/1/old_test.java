@Test
	public void testObtainInternalConnection() throws SQLException, ClassNotFoundException{
		expect(mockPool.getConfig()).andReturn(config).anyTimes();

		testClass.url = "jdbc:mock:driver";
		config.setAcquireRetryDelay(1);
		CustomHook testHook = new CustomHook();
		config.setConnectionHook(testHook);
		
		// make it fail the first time and succeed the second time
		new MockJDBCDriver(new MockJDBCAnswer() {
			
			@SuppressWarnings("synthetic-access")
			public Connection answer() throws SQLException {
				if (count-- > 0){
					throw new SQLException();
				} 
				return mockConnection;
			}
		});
		
		replay(mockPool);
		testClass.obtainInternalConnection();
		// get counts on our hooks
		
		assertEquals(1, testHook.fail);
		assertEquals(1, testHook.acquire);
		
		// Test 2: Same thing but without the hooks
		count=1;
		config.setConnectionHook(null);
		assertEquals(mockConnection, testClass.obtainInternalConnection());
		
		// Test 3: Keep failing
		count=99;
		config.setAcquireRetryAttempts(2);
		try{
			testClass.obtainInternalConnection();
			fail("Should have thrown an exception");
		} catch (SQLException e){
			// expected behaviour
		}
		 
		//	Test 4: Get signalled to interrupt fail delay
		count=99;
		config.setAcquireRetryAttempts(2);
		config.setAcquireRetryDelay(7000);
		final Thread currentThread = Thread.currentThread();

		try{
			new Thread(new Runnable() {
				
//				@Override
				public void run() {
					while (!currentThread.getState().equals(State.TIMED_WAITING)){
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					currentThread.interrupt();
				}
			}).start();
			testClass.obtainInternalConnection();
			fail("Should have thrown an exception");
		} catch (SQLException e){
			// expected behaviour
		}
		config.setAcquireRetryDelay(10);
		
		
	}