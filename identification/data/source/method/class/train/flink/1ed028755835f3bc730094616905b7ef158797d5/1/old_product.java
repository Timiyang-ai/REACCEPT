@Override
	public synchronized void resourceAllocated(JobID jobID, AllocatedResource allocatedResource) {

		List<AllocatedResource> allocatedResources = this.resourcesOfJobs.get(jobID);
		if (allocatedResources == null) {
			allocatedResources = new ArrayList<AllocatedResource>();
			this.resourcesOfJobs.put(jobID, allocatedResources);
		}

		if (allocatedResources.contains(allocatedResource)) {
			throw new IllegalStateException("Resource " + allocatedResource.getAllocationID()
				+ " is already allocated by job " + jobID);
		}

		allocatedResources.add(allocatedResource);
	}