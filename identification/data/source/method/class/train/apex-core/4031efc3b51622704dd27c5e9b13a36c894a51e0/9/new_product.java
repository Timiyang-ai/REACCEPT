public void assignContainer(DeployRequest cdr, String containerId, String containerHost, InetSocketAddress bufferServerAddress) {
    PTContainer container = cdr.container;
    if (container.containerId != null) {
      LOG.info("Removing existing container agent {}", cdr.container.containerId);
      this.containers.remove(container.containerId);
    } else {
      container.bufferServerAddress = bufferServerAddress;
    }
    container.containerId = containerId;
    container.host = containerHost;

    Map<PTOperator, Long> checkpoints = cdr.checkpoints;
    if (checkpoints == null) {
      checkpoints = Collections.emptyMap();
    }
    StreamingContainerContext initCtx = createStramChildInitContext(container.operators, container, checkpoints);
    cdr.setNodes(initCtx.nodeList);
    initCtx.nodeList = new ArrayList<NodeDeployInfo>(0);

    StramChildAgent sca = new StramChildAgent(container, initCtx);
    containers.put(containerId, sca);
    sca.addRequest(cdr);
  }