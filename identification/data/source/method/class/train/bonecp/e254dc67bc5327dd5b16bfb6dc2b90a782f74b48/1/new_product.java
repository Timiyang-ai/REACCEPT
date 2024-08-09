public int getTotalFree(){
		int total=0;
		for (int i=0; i < this.partitionCount; i++){
			total+=this.partitions[i].getAvailableConnections().get();
		}
		return total;
	}