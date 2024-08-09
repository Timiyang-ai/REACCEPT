@Override
	public void schedulJob(final ExecutionGraph executionGraph) throws SchedulingException {

		// First, check if there are enough resources to run this job
		final Map<InstanceType, InstanceTypeDescription> availableInstances = this.instanceManager
			.getMapOfAvailableInstanceTypes();

		for (int i = 0; i < executionGraph.getNumberOfStages(); i++) {

			final Map<InstanceType, Integer> requiredInstanceTypes = new HashMap<InstanceType, Integer>();
			executionGraph.collectInstanceTypesRequiredForStage(i, requiredInstanceTypes, ExecutionState.CREATED);

			final Iterator<Map.Entry<InstanceType, Integer>> it = requiredInstanceTypes.entrySet().iterator();
			while (it.hasNext()) {

				final Map.Entry<InstanceType, Integer> entry = it.next();
				final InstanceTypeDescription descr = availableInstances.get(entry.getKey());
				if (descr == null) {
					throw new SchedulingException("Unable to schedule job: No instance of type " + entry.getKey()
						+ " available");
				}

				if (descr.getMaximumNumberOfAvailableInstances() != -1
					&& descr.getMaximumNumberOfAvailableInstances() < entry.getValue().intValue()) {
					throw new SchedulingException("Unable to schedule job: " + entry.getValue().intValue()
						+ " instances of type " + entry.getKey() + " required, but only "
						+ descr.getMaximumNumberOfAvailableInstances() + " are available");
				}
			}
		}

		// Subscribe to job status notifications
		executionGraph.registerJobStatusListener(this);

		// Set state of each vertex for scheduled
		final ExecutionGraphIterator it2 = new ExecutionGraphIterator(executionGraph, true);
		while (it2.hasNext()) {

			final ExecutionVertex vertex = it2.next();
			if (vertex.getExecutionState() != ExecutionState.CREATED) {
				LOG.error("Execution vertex " + vertex + " has state " + vertex.getExecutionState() + ", expected "
					+ ExecutionState.CREATED);
			}

			vertex.getEnvironment().registerExecutionListener(new QueueExecutionListener(this, vertex));
			vertex.setExecutionState(ExecutionState.SCHEDULED);
		}

		synchronized (this.jobQueue) {
			this.jobQueue.add(executionGraph);
		}
	}