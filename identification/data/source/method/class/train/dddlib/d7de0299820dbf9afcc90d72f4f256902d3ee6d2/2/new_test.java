@Test
	public void testGenerateConnection() {
		//系统数据源，数据源id存在
		DataSource dataSource2 = initSystemDataSourceAndDataSourceIdExist();
		Connection connection2 = dataSource2.generateConnection();
		assertNotNull(connection2);
		
		//自定义数据源，能连接
		DataSource dataSource4 = initCustomDataSourceCanConnect();
		Connection connection4 = dataSource4.generateConnection();
		assertNotNull(connection4);
	}