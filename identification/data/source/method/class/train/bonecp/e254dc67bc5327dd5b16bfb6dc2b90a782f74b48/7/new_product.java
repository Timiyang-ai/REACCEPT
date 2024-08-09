protected void internalReleaseConnection(ConnectionHandle connectionHandle) throws SQLException {
		connectionHandle.clearStatementCaches(false);

		if (connectionHandle.getReplayLog() != null){
			connectionHandle.getReplayLog().clear();
			connectionHandle.recoveryResult.getReplaceTarget().clear();
		}

		if (!this.poolShuttingDown && connectionHandle.isPossiblyBroken() && !isConnectionHandleAlive(connectionHandle)){

			ConnectionPartition connectionPartition = connectionHandle.getOriginatingPartition();
			maybeSignalForMoreConnections(connectionPartition);

			postDestroyConnection(connectionHandle);
			return; // don't place back in queue - connection is broken!
		}


		connectionHandle.setConnectionLastUsed(System.currentTimeMillis());
		if (!this.poolShuttingDown){

			putConnectionBackInPartition(connectionHandle);
		} else {
			connectionHandle.internalClose();
		}
	}