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

    // first entry to count down container start(s), signals ready for operator deployment
    DeployRequest initReq = new DeployRequest(cdr.executeWhenZero, null);
    sca.addRequest(initReq);

    DeployRequest deployRequest = new DeployRequest(cdr.ackCountdown, cdr.executeWhenZero);
    deployRequest.setOperators(container.operators);
    sca.addRequest(deployRequest);
  }