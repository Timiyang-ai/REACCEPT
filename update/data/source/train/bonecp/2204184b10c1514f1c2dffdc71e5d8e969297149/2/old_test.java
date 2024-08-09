@Test
	public void testObtainInternalConnection() throws SQLException, ClassNotFoundException{
		expect(this.mockPool.getConfig()).andReturn(this.config).anyTimes();

		this.testClass.url = "jdbc:mock:driver";
		this.config.setAcquireRetryDelay(1);
		CustomHook testHook = new CustomHook();
		this.config.setConnectionHook(testHook);
		// make it fail the first time and succeed the second time
		expect(this.mockPool.obtainRawInternalConnection()).andThrow(new SQLException()).once().andReturn(this.mockConnection).once();
		replay(this.mockPool);
		this.testClass.obtainInternalConnection();
		// get counts on our hooks
		
		assertEquals(1, testHook.fail);
		assertEquals(1, testHook.acquire);
		
		// Test 2: Same thing but without the hooks
		reset(this.mockPool);
		expect(this.mockPool.getConfig()).andReturn(this.config).anyTimes();
		expect(this.mockPool.obtainRawInternalConnection()).andThrow(new SQLException()).once().andReturn(this.mockConnection).once();
		count=1;
		this.config.setConnectionHook(null);
		replay(this.mockPool);
		assertEquals(this.mockConnection, this.testClass.obtainInternalConnection());
		
		// Test 3: Keep failing
		reset(this.mockPool);
		expect(this.mockPool.getConfig()).andReturn(this.config).anyTimes();
		expect(this.mockPool.obtainRawInternalConnection()).andThrow(new SQLException()).anyTimes();
		replay(this.mockPool);
		count=99;
		this.config.setAcquireRetryAttempts(2);
		try{
			this.testClass.obtainInternalConnection();
			fail("Should have thrown an exception");
		} catch (SQLException e){
			// expected behaviour
		}
		 
		//	Test 4: Get signalled to interrupt fail delay
		count=99;
		this.config.setAcquireRetryAttempts(2);
		this.config.setAcquireRetryDelay(7000);
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
			this.testClass.obtainInternalConnection();
			fail("Should have thrown an exception");
		} catch (SQLException e){
			// expected behaviour
		}
		this.config.setAcquireRetryDelay(10);
		
		
	}