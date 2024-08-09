@Test
	public void testObtainInternalConnection() throws SQLException, ClassNotFoundException, InterruptedException{
		expect(this.mockPool.getConfig()).andReturn(mockConfig).anyTimes();
		DataSource mockDataSourceBean = EasyMock.createNiceMock(DataSource.class);
		expect(this.mockConfig.getDatasourceBean()).andReturn(mockDataSourceBean).anyTimes();

		expect(mockConfig.getJdbcUrl()).andReturn("jdbc:mock:driver").anyTimes();
		mockConfig.setAcquireRetryDelayInMs(1);
		CustomHook testHook = new CustomHook();
		expect(mockConfig.getConnectionHook()).andReturn(testHook).anyTimes();
		// make it fail the first time and succeed the second time
		expect(this.mockPool.getDbIsDown()).andReturn(new AtomicBoolean()).anyTimes();
		expect(mockDataSourceBean.getConnection()).andThrow(new SQLException()).once().andReturn(null).once(); // a call in obtainRaw
		expect(mockConnection.getInternalConnection()).andReturn(new MockConnection()).anyTimes(); // a call in obtainRaw
		 
		replay(this.mockPool, mockConfig, mockDataSourceBean, mockConnection);
		this.testClass.obtainInternalConnection(mockConnection);
		// get counts on our hooks
		
		assertEquals(1, testHook.fail);
		assertEquals(1, testHook.acquire);
	
		// Test 2: Same thing but without the hooks
		reset(this.mockPool, mockDataSourceBean);
		expect(this.mockPool.getDbIsDown()).andReturn(new AtomicBoolean()).anyTimes();
		expect(this.mockPool.getConfig()).andReturn(mockConfig).anyTimes();
		expect(mockDataSourceBean.getConnection()).andThrow(new SQLException()).once().andReturn(this.mockConnection).once();
		int count=1;
		mockConfig.setConnectionHook(null);
		
		replay(this.mockPool, mockDataSourceBean);
		assertEquals(this.mockConnection, this.testClass.obtainInternalConnection(mockConnection));
		
		// Test 3: Keep failing
		reset(this.mockPool, mockDataSourceBean);
		expect(this.mockPool.getConfig()).andReturn(mockConfig).anyTimes();
		expect(mockDataSourceBean.getConnection()).andThrow(new SQLException()).anyTimes();
		replay(this.mockPool, mockDataSourceBean);
		count=99;
		mockConfig.setAcquireRetryAttempts(2);
		try{
			this.testClass.obtainInternalConnection(mockConnection);
			fail("Should have thrown an exception");
		} catch (SQLException e){
			// expected behaviour
		}
		 
		//	Test 4: Get signalled to interrupt fail delay
		count=99;
		mockConfig.setAcquireRetryAttempts(2);
		mockConfig.setAcquireRetryDelayInMs(7000);
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
			this.testClass.obtainInternalConnection(mockConnection);
			fail("Should have thrown an exception");
		} catch (SQLException e){
			// expected behaviour
		}
		
		// Test: No acquire delay
				reset(this.mockPool, mockDataSourceBean, mockConfig);
				expect(this.mockPool.getConfig()).andReturn(mockConfig).anyTimes();
				expect(mockDataSourceBean.getConnection()).andThrow(new SQLException()).anyTimes();
				mockConfig.setAcquireRetryAttempts(0);
				replay(this.mockPool, mockDataSourceBean, mockConfig);
				count=99;
				try{
					this.testClass.obtainInternalConnection(mockConnection);
					fail("Should have thrown an exception");
				} catch (SQLException e){
					// expected behaviour
				}
				
				
		mockConfig.setAcquireRetryDelayInMs(10);
		
		// Test: coverage
	/*	   PowerMock.mockStatic(Thread.class);

		  Thread.sleep(anyLong());
		  expectLastCall().andThrow(new InterruptedException()).anyTimes();
	        PowerMock.replay(Thread.class);
				reset(this.mockPool, mockDataSourceBean);
				expect(this.mockPool.getConfig()).andReturn(mockConfig).anyTimes();
				expect(mockDataSourceBean.getConnection()).andThrow(new SQLException()).anyTimes();
				replay(this.mockPool, mockDataSourceBean);
				count=99;
				mockConfig.setAcquireRetryAttempts(2);
				try{
					this.testClass.obtainInternalConnection(mockConnection);
					fail("Should have thrown an exception");
				} catch (SQLException e){
					// expected behaviour
				}
				*/
	}