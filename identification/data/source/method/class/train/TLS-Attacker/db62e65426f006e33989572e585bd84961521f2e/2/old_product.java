public void commit(Result result) {
	executed++;
	analyzer.analyze(result);
	WorkflowTraceType type = WorkflowTraceTypeManager.generateWorkflowTraceType(result.getExecutedTrace());
	type.clean();
	if (typeSet.add(type) && evoConfig.isSerialize()) {
	    LOG.log(Level.FINE, "Found a new WorkFlowTraceType");
	    LOG.log(Level.FINER, type.toString());
	    File f = new File(evoConfig.getOutputFolder() + "uniqueFlows/" + result.getId());
	    try {
		f.createNewFile();
		WorkflowTraceSerializer.write(f, result.getExecutedTrace());
	    } catch (JAXBException | IOException E) {
		LOG.log(Level.SEVERE,
			"Could not write Results to Disk! Does the Fuzzer have the rights to write to {0}",
			f.getAbsolutePath());
	    }
	}
    }