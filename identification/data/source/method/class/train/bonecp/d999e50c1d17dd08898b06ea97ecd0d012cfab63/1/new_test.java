@Test
	public void testConfigure() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
		expect(mockProperties.getProperty("bonecp.statementsCacheSize")).andReturn("40").anyTimes();
		expect(mockProperties.getProperty("bonecp.minConnectionsPerPartition")).andReturn("20").anyTimes();
		expect(mockProperties.getProperty("bonecp.maxConnectionsPerPartition")).andReturn("50").anyTimes(); 
		expect(mockProperties.getProperty("bonecp.acquireIncrement")).andReturn("5").anyTimes();
		expect(mockProperties.getProperty("bonecp.partitionCount")).andReturn("5").anyTimes();
		expect(mockProperties.getProperty("bonecp.releaseHelperThreads")).andReturn("3").anyTimes();
		expect(mockProperties.getProperty("bonecp.idleConnectionTestPeriod")).andReturn("60").anyTimes();
		expect(mockProperties.getProperty("bonecp.idleMaxAge")).andReturn("240").anyTimes();
		expect(mockProperties.getProperty("javax.persistence.jdbc.url")).andReturn(URL).anyTimes();
		expect(mockProperties.getProperty("javax.persistence.jdbc.user")).andReturn(USERNAME).anyTimes();
		expect(mockProperties.getProperty("javax.persistence.jdbc.password")).andReturn(PASSWORD).anyTimes();
		expect(mockProperties.getProperty("javax.persistence.jdbc.driver")).andReturn(DRIVER).anyTimes();
		expect(mockProperties.getProperty("bonecp.connectionHookClassName")).andReturn("com.jolbox.bonecp.provider.CustomHook").anyTimes();
		expect(mockProperties.getProperty("bonecp.connectionTestStatement")).andReturn(TEST_QUERY).anyTimes();
		expect(mockProperties.getProperty("bonecp.initSQL")).andReturn(TEST_QUERY).anyTimes();
		expect(mockProperties.getProperty("bonecp.logStatementsEnabled")).andReturn("true").anyTimes();



		BoneCPConnectionProvider partialTestClass = createNiceMock(BoneCPConnectionProvider.class, 
				BoneCPConnectionProvider.class.getDeclaredMethod("createPool", BoneCPConfig.class));
		expect(partialTestClass.createPool((BoneCPConfig)anyObject())).andReturn(mockPool).once();
		
		replay(mockProperties, partialTestClass);
		partialTestClass.configure(mockProperties);

		// fetch the configuration object and check that everything is as we passed
		BoneCPConfig config = partialTestClass.getConfig();

		assertEquals(40, config.getStatementsCacheSize());
//		assertEquals(30, config.getStatementsCachedPerConnection());
		assertEquals(20, config.getMinConnectionsPerPartition());
		assertEquals(50, config.getMaxConnectionsPerPartition());
		assertEquals(5, config.getAcquireIncrement());
		assertEquals(5, config.getPartitionCount());
		assertEquals(3, config.getReleaseHelperThreads());
		assertEquals(60*60*1000, config.getIdleConnectionTestPeriod());
		assertEquals(240*60*1000, config.getIdleMaxAge()); 
		assertEquals(URL, config.getJdbcUrl());
		assertEquals(USERNAME, config.getUsername());
		assertEquals(PASSWORD, config.getPassword());
		assertEquals(TEST_QUERY, config.getInitSQL());
		assertEquals(true, config.isLogStatementsEnabled());


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

		testClass.setClassLoader(getClass().getClassLoader());
		testClass.loadClass("java.lang.String");

		testClass.setClassLoader(this.getClass().getClassLoader());
		assertEquals(this.getClass().getClassLoader(), testClass.getClassLoader());
		
		// coverage stuff
		reset(mockProperties);
		expect(mockProperties.getProperty("bonecp.partitionCount")).andReturn("something bad");
		expect(mockProperties.getProperty("bonecp.logStatementsEnabled")).andReturn("something bad");
		expect(mockProperties.getProperty("bonecp.idleMaxAge")).andReturn("something bad");
		replay(mockProperties);
		try{
			testClass.configure(mockProperties);
			fail("Should have failed with exception");
		} catch (HibernateException e){ 
			// do nothing
		}
		
	}