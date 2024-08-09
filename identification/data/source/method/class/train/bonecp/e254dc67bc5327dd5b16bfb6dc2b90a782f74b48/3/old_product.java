public void releaseConnection(Connection conn) throws SQLException {
		try {
			if (this.releaseHelperThreadsConfigured){
				((ConnectionHandle)conn).getOriginatingPartition().getConnectionsPendingRelease().put((ConnectionHandle) conn);
			} else {
				internalReleaseConnection(conn);
			}
		}
		catch (InterruptedException e) {
			throw new SQLException(e);
		}
	}