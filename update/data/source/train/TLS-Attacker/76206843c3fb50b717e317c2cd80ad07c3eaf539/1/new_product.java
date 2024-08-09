public synchronized void restart() {
	if (!this.isFree()) {
	    if (p != null) {
		p.destroy();
	    }
	    try {
		id = LogFileIDManager.getInstance().getID();
		String command = restartServerCommand.replace("[id]", "" + id);
		// System.out.println(command);

		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(command);

		// any error message?
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERR", accepted);

		// any output?
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUT", accepted);

		// kick them off
		errorGobbler.start();
		outputGobbler.start();
		procmon = ProcMon.create(proc);
		while (!outputGobbler.accepted()) {

		}
		// TODO fix for other implementations

		// TODO Error
		// System.out.println("ExitValue: " + exitVal);
	    } catch (IOException t) {
		t.printStackTrace();
	    }
	} else {
	    throw new IllegalStateException("Cant restart a not marked Server. Occupie it first!");
	}

    }