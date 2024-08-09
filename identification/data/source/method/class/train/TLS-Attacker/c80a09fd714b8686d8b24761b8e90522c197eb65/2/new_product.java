public synchronized void restart(String prefix, File certificateFile, File keyFile) {
	if (!this.isFree()) {
	    if (p != null) {
		stop();
	    }
	    try {
		id = LogFileIDManager.getInstance().getID();
		String command = (prefix + restartServerCommand).replace("[id]", "" + id);
		command = command.replace("[output]", traces.getAbsolutePath());
		command = command.replace("[port]", "" + port);
		command = command.replace("[cert]", "" + certificateFile.getAbsolutePath());
		command = command.replace("[key]", "" + keyFile.getAbsolutePath());
		LOG.log(Level.INFO, "Starting Server:" + command);
		long time = System.currentTimeMillis();
		Runtime rt = Runtime.getRuntime();
		p = rt.exec(command);

		// any error message?
		errorGobbler = new StreamGobbler(p.getErrorStream(), "ERR", accepted);

		// any output?
		outputGobbler = new StreamGobbler(p.getInputStream(), "OUT", accepted);

		// kick them off
		errorGobbler.start();
		outputGobbler.start();
		procmon = ProcMon.create(p);
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