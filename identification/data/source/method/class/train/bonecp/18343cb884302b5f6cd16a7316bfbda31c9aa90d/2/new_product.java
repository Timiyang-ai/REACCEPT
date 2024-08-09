protected void internalReleaseConnection(ConnectionHandle connectionHandle) throws InterruptedException, SQLException {

		connectionHandle.clearStatementHandles(false);
		if (connectionHandle.isPossiblyBroken() && !isConnectionHandleAlive(connectionHandle)){

			ConnectionPartition connectionPartition = connectionHandle.getOriginatingPartition();
			maybeSignalForMoreConnections(connectionPartition);

			postDestroyConnection(connectionHandle);
			return; // don't place back in queue - connection is broken!
		}

		connectionHandle.setConnectionLastUsed(System.currentTimeMillis());
		releaseInAnyFreePartition(connectionHandle, connectionHandle.getOriginatingPartition());
	}