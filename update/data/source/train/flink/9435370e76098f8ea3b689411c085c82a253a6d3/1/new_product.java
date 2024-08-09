@Deprecated
	public boolean triggerStackTraceSample(ExecutionJobVertex vertex) {
		synchronized (lock) {
			return triggerStackTraceSampleInternal(vertex);
		}
	}