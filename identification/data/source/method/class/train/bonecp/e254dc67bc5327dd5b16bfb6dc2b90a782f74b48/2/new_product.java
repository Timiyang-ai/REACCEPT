public synchronized boolean onAcquireFail(Throwable t) {
		this.fail++;
		if (this.fail < 3){
			return true; // try 3 times
		} 
		
		return false;

	}