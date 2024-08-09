@Test
	public void testTestConnection() {
		//系统数据源，数据源id存在
		DataSource dataSource = initSystemDataSourceAndDataSourceIdExist();
		boolean result = dataSource.testConnection();
		assertTrue(result);
		
		//自定义数据源，能连接
		DataSource dataSource2 = initCustomDataSourceCanConnect();
		boolean result2 = dataSource2.testConnection();
		assertTrue(result2);
	}