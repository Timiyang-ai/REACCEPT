@Test
	public void testTestConnection() {
		try {
			boolean result = false;
			try {
				//系统数据源，数据源id不存在
				DataSource dataSource = initSystemDataSourceAndDataSourceIdNotExist();
				result = dataSource.testConnection();
				
				assertFalse(true);
			} catch (Exception e) {
				assertTrue(!result);
			}
			
			//系统数据源，数据源id存在
			DataSource dataSource2 = initSystemDataSourceAndDataSourceIdExist();
			boolean result2 = dataSource2.testConnection();
			assertTrue(result2);
			
			//自定义数据源，不能连接
			DataSource dataSource3 = initCustomDataSourceCannotConnect();
			boolean result3 = dataSource3.testConnection();
			assertTrue(!result3);
			
			//自定义数据源，能连接
			DataSource dataSource4 = initCustomDataSourceCanConnect();
			boolean result4 = dataSource4.testConnection();
			assertTrue(result4);
			
		} catch (Exception e) {
			assertFalse(true);
		}
		
	}