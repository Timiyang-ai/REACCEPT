public StramChildAgent assignContainer(ContainerResource resource, InetSocketAddress bufferServerAddr)
  {
    PTContainer container = matchContainer(resource);
    if (container == null) {
      LOG.debug("No container matching allocated resource {}", resource);
      return null;
    }

    container.setState(PTContainer.State.ALLOCATED);
    if (container.getExternalId() != null) {
      LOG.info("Removing existing container agent {}", container.getExternalId());
      this.containers.remove(container.getExternalId());
    }
    container.setExternalId(resource.containerId);
    container.host = resource.host;
    container.bufferServerAddress = bufferServerAddr;
    container.setAllocatedMemoryMB(resource.memoryMB);

    StramChildAgent sca = new StramChildAgent(container, newStreamingContainerContext(), this);
    containers.put(resource.containerId, sca);
    return sca;
  }