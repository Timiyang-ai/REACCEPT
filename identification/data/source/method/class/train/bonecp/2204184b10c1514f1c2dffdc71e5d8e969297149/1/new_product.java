protected void internalReleaseConnection(ConnectionHandle connectionHandle) throws SQLException {
		connectionHandle.clearStatementCaches(false);

		if (connectionHandle.getReplayLog() != null){
			connectionHandle.getReplayLog().clear();
			connectionHandle.recoveryResult.getReplaceTarget().clear();
		}

		if (connectionHandle.isExpired() || (!this.poolShuttingDown && connectionHandle.isPossiblyBroken()
				&& !isConnectionHandleAlive(connectionHandle))){

			ConnectionPartition connectionPartition = connectionHandle.getOriginatingPartition();
			maybeSignalForMoreConnections(connectionPartition);

			postDestroyConnection(connectionHandle);
			connectionHandle.clearStatementCaches(true);
			return; // don't place back in queue - connection is broken or expired.
		}


		connectionHandle.setConnectionLastUsedInMs(System.currentTimeMillis());
		if (!this.poolShuttingDown){

			putConnectionBackInPartition(connectionHandle);
		} else {
			connectionHandle.internalClose();
		}
	}