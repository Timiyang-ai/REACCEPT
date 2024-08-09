public Connection generateConnection() {
        Connection connection = null;
        
        if (dataSourceType.equals(DataSourceType.SYSTEM_DATA_SOURCE)) {
            javax.sql.DataSource dataSource = InstanceFactory.getInstance(javax.sql.DataSource.class, dataSourceId);
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            DbUtils.loadDriver(jdbcDriver);
            try {
                connection = DriverManager.getConnection(connectUrl, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return connection;
    }