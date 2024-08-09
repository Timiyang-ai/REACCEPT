@SuppressWarnings("resource")
	public void clear() {
        Connection sqlConn = null;
        PreparedStatement ps = null;
        try {
            
            sqlConn = dataSource.getConnection();
            
            ps = sqlConn.prepareStatement(SQL_DELETE_ALL_CUSTOMPROPERTIES);
            ps.executeUpdate();
            
            ps = sqlConn.prepareStatement(SQL_DELETE_ALL_ROLES);
            ps.executeUpdate();
            
            ps = sqlConn.prepareStatement(SQL_DELETE_ALL_FEATURES);
            ps.executeUpdate();
            
        } catch (SQLException sqlEX) {
            throw new FeatureAccessException("Cannot check feature existence, error related to database", sqlEX);
        } finally {
            closeStatement(ps);
            closeConnection(sqlConn);
        }
    }