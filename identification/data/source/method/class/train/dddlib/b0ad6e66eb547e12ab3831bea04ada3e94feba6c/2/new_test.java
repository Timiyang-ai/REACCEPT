@Test
	public void testGetSystemDataSource() {
		//不存在的系统数据源
		String systemDataSourceIdNotExist = "aaaaabbb";
		//存在的系统数据源
		String systemDataSourceIdExist = "dataSource_gqc";
		try {
			DataSource dataSourceNotExist = null;
			try {
				//当使用不存在的系统数据源时，这里会抛出异常
				dataSourceNotExist = DataSource
						.getSystemDataSource(systemDataSourceIdNotExist);
				//如果能走到这里，说明systemDataSourceIdNotExist是存在的系统数据源，不符合测试预期：系统数据源不存在
				assertFalse(true);
			} catch (Exception e) {
				assertNull(dataSourceNotExist);
			}
			
			DataSource dataSourceExist = null;
			try {
				dataSourceExist = DataSource.getSystemDataSource(systemDataSourceIdExist);
			} catch (Exception e) {
				fail("获取存在的系统数据源失败！");
			}
			assertNotNull(dataSourceExist);
			
		} catch (Exception e) {
			assertFalse(true);
		}
	}