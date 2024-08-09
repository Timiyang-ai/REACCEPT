@Override
	public void executionStateChanged(Environment ee, ExecutionState newExecutionState, String optionalMessage) {

		switch (newExecutionState) {
		case RUNNING:
			this.taskManagerProfiler.registerMainThreadForCPUProfiling(ee, ee.getExecutingThread(),
				this.executionVertexID);
			break;
		case FINISHING:
		case FINISHED:
		case CANCELING:
		case CANCELED:
		case RESTARTING:
		case FAILED:
			this.taskManagerProfiler.unregisterMainThreadFromCPUProfiling(ee, ee.getExecutingThread());
			break;
		default:
			LOG.error("Unexpected state transition to " + newExecutionState + " for vertex " + this.executionVertexID);
			break;
		}
	}