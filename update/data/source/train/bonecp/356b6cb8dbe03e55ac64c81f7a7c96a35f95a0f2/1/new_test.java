@Test
	public void testConfigure() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException {
		expect(mockProperties.getProperty(CONFIG_TEST_STATEMENT)).andReturn(null).anyTimes();
		expect(mockProperties.getProperty(CONFIG_PREPARED_STATEMENT_CACHE_SIZE)).andReturn("40").anyTimes();
		expect(mockProperties.getProperty(CONFIG_STATEMENTS_CACHED_PER_CONNECTION)).andReturn("30").anyTimes();
		expect(mockProperties.getProperty(CONFIG_MIN_CONNECTIONS_PER_PARTITION)).andReturn("20").anyTimes();
		expect(mockProperties.getProperty(CONFIG_MAX_CONNECTIONS_PER_PARTITION)).andReturn("50").anyTimes(); 
		expect(mockProperties.getProperty(CONFIG_ACQUIRE_INCREMENT)).andReturn("5").anyTimes();
		expect(mockProperties.getProperty(CONFIG_PARTITION_COUNT)).andReturn("5").anyTimes();
		expect(mockProperties.getProperty(CONFIG_RELEASE_HELPER_THREADS)).andReturn("3").anyTimes();
		expect(mockProperties.getProperty(CONFIG_IDLE_CONNECTION_TEST_PERIOD)).andReturn("60").anyTimes();
		expect(mockProperties.getProperty(CONFIG_IDLE_MAX_AGE)).andReturn("240").anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_URL, "JDBC URL NOT SET IN CONFIG")).andReturn(URL).anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_USERNAME, "username not set")).andReturn(USERNAME).anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_PASSWORD, "password not set")).andReturn(PASSWORD).anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_DRIVER_CLASS)).andReturn(DRIVER).anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_HOOK_CLASS)).andReturn("com.jolbox.bonecp.CustomHook").anyTimes();
		expect(mockProperties.getProperty(CONFIG_INIT_SQL)).andReturn(CommonTestUtils.TEST_QUERY).anyTimes();


		BoneCPConnectionProvider partialTestClass = createNiceMock(BoneCPConnectionProvider.class, 
				BoneCPConnectionProvider.class.getDeclaredMethod("createPool", BoneCPConfig.class));
		expect(partialTestClass.createPool((BoneCPConfig)anyObject())).andReturn(mockPool).once();
		
		replay(mockProperties, partialTestClass);
		partialTestClass.configure(mockProperties);

		// fetch the configuration object and check that everything is as we passed
		BoneCPConfig config = partialTestClass.getConfig();

		assertEquals(40, config.getPreparedStatementsCacheSize());
		assertEquals(30, config.getStatementsCachedPerConnection());
		assertEquals(20, config.getMinConnectionsPerPartition());
		assertEquals(50, config.getMaxConnectionsPerPartition());
		assertEquals(5, config.getAcquireIncrement());
		assertEquals(5, config.getPartitionCount());
		assertEquals(3, config.getReleaseHelperThreads());
		assertEquals(60, config.getIdleConnectionTestPeriod());
		assertEquals(240, config.getIdleMaxAge()); 
		assertEquals(URL, config.getJdbcUrl());
		assertEquals(USERNAME, config.getUsername());
		assertEquals(PASSWORD, config.getPassword());
		assertEquals(CommonTestUtils.TEST_QUERY, config.getInitSQL());


		verify(mockProperties, partialTestClass);
		reset(mockProperties);
		expect(mockProperties.getProperty(CONFIG_CONNECTION_DRIVER_CLASS)).andReturn(null).anyTimes();
		replay(mockProperties);
		try{
			testClass.configure(mockProperties);
			fail("Should have failed with exception");
		} catch (HibernateException e){ 
			// do nothing
		}

		reset(mockProperties);
		expect(mockProperties.getProperty(CONFIG_CONNECTION_DRIVER_CLASS)).andReturn(DRIVER).anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_URL, "JDBC URL NOT SET IN CONFIG")).andReturn("somethinginvalid").anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_USERNAME, "username not set")).andReturn(USERNAME).anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_PASSWORD, "password not set")).andReturn(PASSWORD).anyTimes();

		replay(mockProperties);
		try{
			testClass.configure(mockProperties);
			fail("Should have failed with exception");
		} catch (HibernateException e){
			// do nothing
		}
		

		verify(mockProperties);
		reset(mockProperties);
		expect(mockProperties.getProperty(CONFIG_CONNECTION_DRIVER_CLASS)).andReturn("somethingbad").anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_URL, "JDBC URL NOT SET IN CONFIG")).andReturn("somethinginvalid").anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_USERNAME, "username not set")).andReturn(USERNAME).anyTimes();
		expect(mockProperties.getProperty(CONFIG_CONNECTION_PASSWORD, "password not set")).andReturn(PASSWORD).anyTimes();

		replay(mockProperties);
		try{
			testClass.configure(mockProperties);
			fail("Should have failed with exception");
		} catch (HibernateException e){ 
			// do nothing
		}

	}