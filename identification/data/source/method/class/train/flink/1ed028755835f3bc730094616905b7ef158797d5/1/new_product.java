@Override
	public synchronized void resourcesAllocated(final JobID jobID, final List<AllocatedResource> allocatedResources) {

		List<AllocatedResource> allocatedResourcesOfJob = this.resourcesOfJobs.get(jobID);
		if (allocatedResourcesOfJob == null) {
			allocatedResourcesOfJob = new ArrayList<AllocatedResource>();
			this.resourcesOfJobs.put(jobID, allocatedResourcesOfJob);
		}

		for (final AllocatedResource allocatedResource : allocatedResources) {
			if (allocatedResourcesOfJob.contains(allocatedResource)) {
				throw new IllegalStateException("Resource " + allocatedResource.getAllocationID()
					+ " is already allocated by job " + jobID);
			}

			allocatedResourcesOfJob.add(allocatedResource);
		}
	}