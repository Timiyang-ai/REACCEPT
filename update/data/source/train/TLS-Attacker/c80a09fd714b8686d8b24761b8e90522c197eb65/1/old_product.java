public synchronized void restart(String prefix) {
	if (!this.isFree()) {
	    if (p != null) {
		p.destroy();
	    }
	    try {
		id = LogFileIDManager.getInstance().getID();
		String command = (prefix + restartServerCommand).replace("[id]", "" + id);
		command = command.replace("[output]", traces.getAbsolutePath());
		command = command.replace("[port]", "" + port);
		// System.out.println(command);
		long time = System.currentTimeMillis();
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec(command);

		// any error message?
		errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERR", accepted);

		// any output?
		outputGobbler = new StreamGobbler(proc.getInputStream(), "OUT", accepted);

		// kick them off
		errorGobbler.start();
		outputGobbler.start();
		procmon = ProcMon.create(proc);
		while (!outputGobbler.accepted()) {

		    try {
			Thread.sleep(50);
		    } catch (InterruptedException ex) {
			Logger.getLogger(TLSServer.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    if (System.currentTimeMillis() - time >= ConfigManager.getInstance().getConfig().getTimeout()) {
			throw new RuntimeException("Timeout in StreamGobler, Server never finished starting");
		    }
		}
	    } catch (IOException t) {
		t.printStackTrace();
	    }

	} else {
	    throw new IllegalStateException("Cant restart a not marked Server. Occupie it first!");
	}

    }