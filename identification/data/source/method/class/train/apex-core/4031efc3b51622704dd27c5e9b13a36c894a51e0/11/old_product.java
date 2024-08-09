public StramChildAgent assignContainer(ContainerResource resource, InetSocketAddress bufferServerAddr)
  {
    PTContainer container = matchContainer(resource);
    if (container == null) {
      LOG.debug("No container matching allocated resource {}", resource);
      return null;
    }

    pendingAllocation.remove(container);
    container.setState(PTContainer.State.ALLOCATED);
    if (container.getExternalId() != null) {
      LOG.info("Removing existing container agent {}", container.getExternalId());
      this.containers.remove(container.getExternalId());
    }
    container.setExternalId(resource.containerId);
    container.host = resource.host;
    container.bufferServerAddress = bufferServerAddr;
    container.nodeHttpAddress = resource.nodeHttpAddress;
    container.setAllocatedMemoryMB(resource.memoryMB);
    container.getPendingUndeploy().clear();

    StramChildAgent sca = new StramChildAgent(container, newStreamingContainerContext(resource.containerId), this);
    containers.put(resource.containerId, sca);
    return sca;
  }