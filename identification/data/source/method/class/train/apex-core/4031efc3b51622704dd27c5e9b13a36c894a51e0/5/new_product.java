public void assignContainer(ContainerStartRequest cdr, String containerId, String containerHost, InetSocketAddress bufferServerAddr)
  {
    PTContainer container = cdr.container;
    if (container.containerId != null) {
      LOG.info("Removing existing container agent {}", cdr.container.containerId);
      this.containers.remove(container.containerId);
    }
    container.containerId = containerId;
    container.host = containerHost;
    container.bufferServerAddress = bufferServerAddr;

    StramChildAgent sca = new StramChildAgent(container, newStreamingContainerContext());
    containers.put(containerId, sca);
  }