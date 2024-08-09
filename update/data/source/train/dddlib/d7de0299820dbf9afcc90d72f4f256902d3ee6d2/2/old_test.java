@Test
	public void testGenerateConnection() {
		try {
			Connection connection = null;
			try {
				//系统数据源，数据源id不存在
				DataSource dataSource = initSystemDataSourceAndDataSourceIdNotExist();
				connection = dataSource.generateConnection();
				
				assertFalse(true);
			} catch (Exception e) {
				assertNull(connection);
			}
			
			//系统数据源，数据源id存在
			DataSource dataSource2 = initSystemDataSourceAndDataSourceIdExist();
			Connection connection2 = dataSource2.generateConnection();
			assertNotNull(connection2);
			
			//自定义数据源，不能连接
			DataSource dataSource3 = initCustomDataSourceCannotConnect();
			Connection connection3 = dataSource3.generateConnection();
			assertNull(connection3);
			
			//自定义数据源，能连接
			DataSource dataSource4 = initCustomDataSourceCanConnect();
			Connection connection4 = dataSource4.generateConnection();
			assertNotNull(connection4);
			
		} catch (Exception e) {
			assertFalse(true);
		}
	}