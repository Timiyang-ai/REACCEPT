public synchronized void start() {

	// You have to ooccupie a Server to start it
	if (!this.isFree()) {
	    if (p != null) {
		p.destroy();
	    }
	    restart();
	} else {
	    throw new IllegalStateException("Cant start a not marked Server. Occupie it first!");
	}
    }