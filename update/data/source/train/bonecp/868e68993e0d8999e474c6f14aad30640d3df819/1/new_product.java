protected void postDestroyConnection(ConnectionHandle handle){
		ConnectionPartition partition = handle.getOriginatingPartition();
	
		if (this.finalizableRefQueue != null){ //safety
			this.finalizableRefs.remove(handle.getInternalConnection()); 
//			assert o != null : "Did not manage to remove connection from finalizable ref queue";
		}
		
		partition.updateCreatedConnections(-1);
		partition.setUnableToCreateMoreTransactions(false); // we can create new ones now, this is an optimization

		
		// "Destroying" for us means: don't put it back in the pool.
		if (handle.getConnectionHook() != null){
			handle.getConnectionHook().onDestroy(handle);
		}

	}