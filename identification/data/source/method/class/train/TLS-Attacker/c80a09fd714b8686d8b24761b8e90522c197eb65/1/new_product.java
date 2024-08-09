public synchronized void start(String prefix, File certificateFile, File keyFile) {

	// You have to ooccupie a Server to start it
	if (!this.isFree()) {
	    if (p != null) {
		stop();
	    }
	    restart(prefix, certificateFile, keyFile);
	} else {
	    throw new IllegalStateException("Cant start a not marked Server. Occupie it first!");
	}
    }