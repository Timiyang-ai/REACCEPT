@SuppressWarnings("unchecked")
	public void triggerStackTraceSample(ExecutionJobVertex vertex) {
		synchronized (lock) {
			if (shutDown) {
				return;
			}

			if (!pendingStats.contains(vertex)) {
				pendingStats.add(vertex);

				Future<StackTraceSample> sample = coordinator.triggerStackTraceSample(
						vertex.getTaskVertices(),
						numSamples,
						delayBetweenSamples,
						MAX_STACK_TRACE_DEPTH);

				sample.onComplete(new StackTraceSampleCompletionCallback(
						vertex), vertex.getGraph().getExecutionContext());
			}
		}
	}