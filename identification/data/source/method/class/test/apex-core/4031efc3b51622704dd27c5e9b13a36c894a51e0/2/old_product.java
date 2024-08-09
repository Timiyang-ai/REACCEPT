public StramChildAgent assignContainer(ContainerResource resource, InetSocketAddress bufferServerAddr)
  {
    PTContainer container = null;
    // match container waiting for resource
    for (PTContainer c : pendingAllocation) {
      if (c.getState() == PTContainer.State.NEW || c.getState() == PTContainer.State.KILLED) {
        if (c.getResourceRequestPriority() == resource.priority) {
          container = c;
        }
      }
    }

    if (container == null) {
      LOG.debug("No container matching allocated resource {}", resource);
      LOG.debug("Containers waiting for allocation {}", pendingAllocation);
      return null;
    }

    pendingAllocation.remove(container);
    container.setState(PTContainer.State.ALLOCATED);
    if (container.getExternalId() != null) {
      LOG.info("Replacing container agent {}", container.getExternalId());
      this.containers.remove(container.getExternalId());
    }
    container.setExternalId(resource.containerId);
    container.host = resource.host;
    container.bufferServerAddress = bufferServerAddr;
    container.nodeHttpAddress = resource.nodeHttpAddress;
    container.setAllocatedMemoryMB(resource.memoryMB);

    StramChildAgent sca = new StramChildAgent(container, newStreamingContainerContext(resource.containerId), this);
    containers.put(resource.containerId, sca);
    return sca;
  }