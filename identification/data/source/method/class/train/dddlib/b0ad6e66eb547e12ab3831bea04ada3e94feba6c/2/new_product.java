public static DataSource getSystemDataSource(String dataSourceId) throws Exception {
		DataSource result = null;
		Connection conn = null;
		javax.sql.DataSource dataSource = null;
		
		try {
			dataSource = InstanceFactory.getInstance(javax.sql.DataSource.class, dataSourceId);
		} catch (Exception e) {
			throw new RuntimeException("该系统数据源不存在！",e);
		}
		
		try {
			conn = dataSource.getConnection();
		    result = new DataSource();
		    result.setDataSourceId(dataSourceId);
		    result.setDataSourceType(DataSourceType.SYSTEM_DATA_SOURCE);
			result.setConnectUrl(conn.getMetaData().getURL());
			result.setUsername(conn.getMetaData().getUserName());
		} catch (Exception e) {
			throw new RuntimeException("获取系统数据源失败！",e);
		}finally{
			if(conn != null){
				conn.close();
			}
		}
		
		return result;
	}