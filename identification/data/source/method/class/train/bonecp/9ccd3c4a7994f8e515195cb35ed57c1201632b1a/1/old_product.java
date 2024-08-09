protected void releaseConnection(Connection connection) throws SQLException {
		ConnectionHandle handle = (ConnectionHandle)connection;

		// hook calls
		if (handle.getConnectionHook() != null){
			handle.getConnectionHook().onCheckIn(handle);
		}

		// release immediately or place it in a queue so that another thread will eventually close it. If we're shutting down,
		// close off the connection right away because the helper threads have gone away.
		if (!this.poolShuttingDown && this.releaseHelperThreadsConfigured && !this.cachedPoolStrategy){
			if (!handle.getOriginatingPartition().getConnectionsPendingRelease().tryTransfer(handle)){
				handle.getOriginatingPartition().getConnectionsPendingRelease().put(handle);
			}
		} else {
			internalReleaseConnection(handle);
		}
	}