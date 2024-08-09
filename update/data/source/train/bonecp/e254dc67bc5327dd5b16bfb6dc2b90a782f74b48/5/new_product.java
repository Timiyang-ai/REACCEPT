public void releaseConnection(Connection connection) throws SQLException {
		
		try {
			ConnectionHandle handle = (ConnectionHandle)connection;
			
			// hook calls
			if (handle.getConnectionHook() != null){
				handle.getConnectionHook().onCheckIn(handle);
			}
			
			if (this.releaseHelperThreadsConfigured){
				handle.getOriginatingPartition().getConnectionsPendingRelease().put(handle);
			} else {
				internalReleaseConnection(handle);
			}
		}
		catch (InterruptedException e) {
			throw new SQLException(e);
		}
	}