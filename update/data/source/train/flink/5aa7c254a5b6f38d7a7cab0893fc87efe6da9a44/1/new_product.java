@SuppressWarnings("unchecked")
	public boolean triggerStackTraceSample(ExecutionJobVertex vertex) {
		synchronized (lock) {
			if (shutDown) {
				return false;
			}

			if (!pendingStats.contains(vertex) &&
					!vertex.getGraph().getState().isTerminalState()) {

				ExecutionContext executionContext = vertex.getGraph().getExecutionContext();

				// Only trigger if still active job
				if (executionContext != null) {
					pendingStats.add(vertex);

					Future<StackTraceSample> sample = coordinator.triggerStackTraceSample(
							vertex.getTaskVertices(),
							numSamples,
							delayBetweenSamples,
							MAX_STACK_TRACE_DEPTH);

					sample.onComplete(new StackTraceSampleCompletionCallback(vertex), executionContext);

					return true;
				}
			}

			return false;
		}
	}