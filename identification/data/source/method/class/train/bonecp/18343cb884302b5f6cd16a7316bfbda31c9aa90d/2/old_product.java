protected void internalReleaseConnection(Connection conn) throws InterruptedException, SQLException {

		ConnectionHandle connectionHandle = (ConnectionHandle)conn;
		connectionHandle.clearStatementHandles(false);
		if (connectionHandle.isPossiblyBroken() && !isConnectionHandleAlive(connectionHandle)){

			ConnectionPartition connectionPartition = connectionHandle.getOriginatingPartition();
			connectionPartition.setUnableToCreateMoreTransactions(false);
			maybeSignalForMoreConnections(connectionPartition);
			connectionPartition.updateCreatedConnections(-1);
			return; // don't place back in queue - connection is broken!
		}

		connectionHandle.setConnectionLastUsed(System.currentTimeMillis());
		releaseInAnyFreePartition(connectionHandle, connectionHandle.getOriginatingPartition());
	}