public static DataSource getSystemDataSource(String dataSourceId) throws SQLException {
		DataSource result = null;
		javax.sql.DataSource dataSource = InstanceFactory.getInstance(javax.sql.DataSource.class, dataSourceId);
		
		if (dataSource != null) {
            Connection conn = dataSource.getConnection();
		    result = new DataSource();
		    result.setDataSourceId(dataSourceId);
		    result.setDataSourceType(DataSourceType.SYSTEM_DATA_SOURCE);
			result.setConnectUrl(conn.getMetaData().getURL());
			result.setUsername(conn.getMetaData().getUserName());
			conn.close();
		}
		
		return result;
	}