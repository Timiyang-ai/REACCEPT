public synchronized void connect() throws RpcException {
    if (connected) return;

    if (clusterCoordinator == null) {
      try {
        this.clusterCoordinator = new ZKClusterCoordinator(this.config);
        this.clusterCoordinator.start(10000);
      } catch (Exception e) {
        throw new RpcException("Failure setting up ZK for client.", e);
      }
    }

    Collection<DrillbitEndpoint> endpoints = clusterCoordinator.getAvailableEndpoints();
    checkState(!endpoints.isEmpty(), "No DrillbitEndpoint can be found");
    // just use the first endpoint for now
    DrillbitEndpoint endpoint = endpoints.iterator().next();
    this.client = new UserClient(allocator, new NioEventLoopGroup(config.getInt(ExecConstants.CLIENT_RPC_THREADS), new NamedThreadFactory("Client-")));
    logger.debug("Connecting to server {}:{}", endpoint.getAddress(), endpoint.getUserPort());
    connect(endpoint);
    connected = true;
  }