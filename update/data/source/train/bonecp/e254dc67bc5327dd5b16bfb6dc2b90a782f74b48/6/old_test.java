@Test
	public void testOnAcquireFail() throws SQLException {
		hookClass = new CustomHook();
		reset(mockConfig);
		expect(mockConfig.getPartitionCount()).andReturn(1).anyTimes();
		expect(mockConfig.getMaxConnectionsPerPartition()).andReturn(5).anyTimes();
		expect(mockConfig.getMinConnectionsPerPartition()).andReturn(5).anyTimes();
		expect(mockConfig.getIdleConnectionTestPeriod()).andReturn(10000L).anyTimes();
		expect(mockConfig.getUsername()).andReturn(CommonTestUtils.username).anyTimes();
		expect(mockConfig.getPassword()).andReturn(CommonTestUtils.password).anyTimes();
		expect(mockConfig.getJdbcUrl()).andReturn("somethingbad").anyTimes();
		expect(mockConfig.getReleaseHelperThreads()).andReturn(0).anyTimes();
		expect(mockConfig.getConnectionHook()).andReturn(hookClass).anyTimes();
		replay(mockConfig);
		
		try{
			poolClass = new BoneCP(mockConfig);	
			poolClass.getConnection();
		} catch (Exception e){
			// do nothing
		}
		assertEquals(1, hookClass.fail);
		
	}