@SuppressWarnings("unchecked")
	public boolean triggerStackTraceSample(ExecutionJobVertex vertex) {
		synchronized (lock) {
			if (shutDown) {
				return false;
			}

			if (!pendingStats.contains(vertex) &&
					!vertex.getGraph().getState().isGloballyTerminalState()) {

				ExecutionContext executionContext = vertex.getGraph().getExecutionContext();

				// Only trigger if still active job
				if (executionContext != null) {
					pendingStats.add(vertex);

					if (LOG.isDebugEnabled()) {
						LOG.debug("Triggering stack trace sample for tasks: " + Arrays.toString(vertex.getTaskVertices()));
					}

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