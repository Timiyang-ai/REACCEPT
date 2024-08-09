public boolean testConnection() {
		boolean result = false;
        Connection connection = null;
		
		if (dataSourceType.equals(DataSourceType.SYSTEM_DATA_SOURCE)) {
		    javax.sql.DataSource dataSource = null;
			try {
				dataSource = InstanceFactory.getInstance(
						javax.sql.DataSource.class, dataSourceId);
			} catch (Exception e) {
				throw new SystemDataSourceNotExistException("系统数据源不存在！",e);
			}
			
		    try {
                connection = dataSource.getConnection();
                if (connection != null) {
                    result = true;
                    connection.close();
                }
            } catch (SQLException e) {
            	throw new RuntimeException("获取系统数据源连接失败！",e);
            }
		    
		    return result;
		}
		
        DbUtils.loadDriver(jdbcDriver);
        try {
            connection = DriverManager.getConnection(connectUrl, username, password);
            if (connection != null) {
            	result = true;
            }
        } catch (SQLException e) {
        	throw new RuntimeException("获取自定义数据源连接失败！",e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
		
        return result;
	}