@Override
	public void resourceAllocated(final JobID jobID, final AllocatedResource allocatedResource) {

		if (allocatedResource == null) {
			LOG.error("Resource to lock is null!");
			return;
		}

		if (allocatedResource.getInstance() instanceof DummyInstance) {
			LOG.debug("Available instance is of type DummyInstance!");
			return;
		}

		// Check if all required libraries are available on the instance
		try {
			allocatedResource.getInstance().checkLibraryAvailability(jobID);
		} catch (IOException ioe) {
			LOG.error("Cannot check library availability: " + StringUtils.stringifyException(ioe));
		}

		synchronized (this.jobQueue) {

			final ExecutionGraph eg = getExecutionGraphByID(jobID);
			if (eg == null) {
				/*
				 * The job have have been canceled in the meantime, in this case
				 * we release the instance immediately.
				 */
				try {
					this.instanceManager.releaseAllocatedResource(jobID, null, allocatedResource);
				} catch (InstanceException e) {
					LOG.error(e);
				}
				return;
			}

			AllocatedResource resourceToBeReplaced = null;
			// Important: only look for instances to be replaced in the current stage
			ExecutionGraphIterator it = new ExecutionGraphIterator(eg, eg.getIndexOfCurrentExecutionStage(), true, true);
			while (it.hasNext()) {

				final ExecutionVertex vertex = it.next();
				if (vertex.getExecutionState() == ExecutionState.ASSIGNING && vertex.getAllocatedResource() != null) {
					// In local mode, we do not consider any topology, only the instance type
					if (vertex.getAllocatedResource().getInstanceType().equals(
						allocatedResource.getInstanceType())) {
						resourceToBeReplaced = vertex.getAllocatedResource();
						break;
					}
				}
			}

			// For some reason, we don't need this instance
			if (resourceToBeReplaced == null) {
				LOG.warn("Instance " + allocatedResource.getInstance() + " is not required for job" + eg.getJobID());
				try {
					this.instanceManager.releaseAllocatedResource(jobID, eg.getJobConfiguration(), allocatedResource);
				} catch (InstanceException e) {
					LOG.error(e);
				}
				return;
			}

			// Replace the selected instance in the entire graph with the new instance
			it = new ExecutionGraphIterator(eg, true);
			while (it.hasNext()) {
				final ExecutionVertex vertex = it.next();
				if (vertex.getAllocatedResource().equals(resourceToBeReplaced)) {
					vertex.setAllocatedResource(allocatedResource);
					vertex.setExecutionState(ExecutionState.ASSIGNED);
				}
			}
		}

	}