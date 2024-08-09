public boolean testConnection() {
		boolean result = false;
        Connection connection = null;
		
		if (dataSourceType.equals(DataSourceType.SYSTEM_DATA_SOURCE)) {
		    javax.sql.DataSource dataSource = InstanceFactory.getInstance(javax.sql.DataSource.class, dataSourceId);
		    try {
                connection = dataSource.getConnection();
                if (connection != null) {
                    result = true;
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(connection);
        }
		
        return result;
	}