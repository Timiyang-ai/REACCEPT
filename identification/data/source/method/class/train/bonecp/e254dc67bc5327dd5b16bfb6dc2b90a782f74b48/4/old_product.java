protected void internalReleaseConnection(ConnectionHandle connectionHandle) throws InterruptedException, SQLException {

		// close off tracked statements.
		connectionHandle.clearStatementHandles(false);

		if (!this.poolShuttingDown && connectionHandle.isPossiblyBroken() && !isConnectionHandleAlive(connectionHandle)){

			ConnectionPartition connectionPartition = connectionHandle.getOriginatingPartition();
			maybeSignalForMoreConnections(connectionPartition);

			postDestroyConnection(connectionHandle);
			return; // don't place back in queue - connection is broken!
		}

		connectionHandle.setConnectionLastUsed(System.currentTimeMillis());
		if (!this.poolShuttingDown){ 
			releaseInAnyFreePartition(connectionHandle, connectionHandle.getOriginatingPartition());
		} else {
			connectionHandle.internalClose();
		}
	}