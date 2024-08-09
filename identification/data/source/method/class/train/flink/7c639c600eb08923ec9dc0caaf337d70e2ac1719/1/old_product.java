@SuppressWarnings("unchecked")
	public boolean triggerStackTraceSample(ExecutionJobVertex vertex) {
		synchronized (lock) {
			if (shutDown) {
				return false;
			}

			if (!pendingStats.contains(vertex) &&
					!vertex.getGraph().getState().isGloballyTerminalState()) {

				Executor executor = vertex.getGraph().getFutureExecutor();

				// Only trigger if still active job
				if (executor != null) {
					pendingStats.add(vertex);

					if (LOG.isDebugEnabled()) {
						LOG.debug("Triggering stack trace sample for tasks: " + Arrays.toString(vertex.getTaskVertices()));
					}

					Future<StackTraceSample> sample = coordinator.triggerStackTraceSample(
							vertex.getTaskVertices(),
							numSamples,
							delayBetweenSamples,
							MAX_STACK_TRACE_DEPTH);

					sample.handleAsync(new StackTraceSampleCompletionCallback(vertex), executor);

					return true;
				}
			}

			return false;
		}
	}