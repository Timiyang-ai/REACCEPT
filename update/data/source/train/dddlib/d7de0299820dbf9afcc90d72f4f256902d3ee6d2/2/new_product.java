public Connection generateConnection() {
        Connection connection = null;
        
        if (dataSourceType.equals(DataSourceType.SYSTEM_DATA_SOURCE)) {
            javax.sql.DataSource dataSource;
			try {
				dataSource = InstanceFactory.getInstance(
						javax.sql.DataSource.class, dataSourceId);
			} catch (Exception e) {
				throw new SystemDataSourceNotExistException("系统数据源不存在！",e);
			}
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
            	throw new RuntimeException("获取系统数据源连接失败！",e);
            }
        }else{
            DbUtils.loadDriver(jdbcDriver);
            try {
                connection = DriverManager.getConnection(connectUrl, username, password);
            } catch (SQLException e) {
            	throw new RuntimeException("获取自定义数据源连接失败！",e);
            }
        }
        
        return connection;
    }