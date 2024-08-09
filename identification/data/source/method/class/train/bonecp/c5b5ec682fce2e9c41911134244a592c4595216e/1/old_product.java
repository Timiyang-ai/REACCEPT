protected void releaseInAnyFreePartition(ConnectionHandle connectionHandle, ConnectionPartition activePartition) throws InterruptedException  {

		ConnectionPartition workingPartition = activePartition;
		if (!workingPartition.getFreeConnections().offer(connectionHandle)){
			// we ran out of space on this partition, pick another free one
			boolean released = false;
			for (int i=0; i < this.partitionCount; i++){
				if (this.partitions[i].getFreeConnections().offer(connectionHandle)){
					released=true;
					break;
				}
			}

			if (!released)	{
				// we still didn't find an empty one, wait forever until our partition is free
				connectionHandle.getOriginatingPartition().getFreeConnections().put(connectionHandle);
			}
		}

	}