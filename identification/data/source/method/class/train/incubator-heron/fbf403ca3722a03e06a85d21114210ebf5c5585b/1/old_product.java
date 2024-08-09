public static Pair<String, List<Process>> setupZkTunnel(Config config) {
    // Remove all spaces
    String connectionString = Context.stateManagerConnectionString(config).replaceAll("\\s+", "");

    // Read values from config
    String tunnelHost = ZkContext.tunnelHost(config);
    int timeout = ZkContext.tunnelConnectionTimeoutMs(config);
    int retry = ZkContext.tunnelConnectionRetryCount(config);
    int retryInterval = ZkContext.tunnelRetryIntervalMs(config);
    int verifyCount = ZkContext.tunnelVerifyCount(config);
    boolean isVerbose = Context.verbose(config);

    List<Pair<InetSocketAddress, Process>> ret = new ArrayList<>();

    // For zookeeper, connection String can be a list of host:port, separated by comma
    String[] endpoints = connectionString.split(",");
    for (String endpoint : endpoints) {
      InetSocketAddress address = NetworkUtils.getInetSocketAddress(endpoint);

      // Get the tunnel process if needed
      Pair<InetSocketAddress, Process> pair =
          NetworkUtils.establishSSHTunnelIfNeeded(
              address, tunnelHost, timeout, retry, retryInterval, verifyCount, isVerbose);

      ret.add(pair);
    }

    // Construct the new ConnectionString and tunnel processes
    StringBuilder connectionStringBuilder = new StringBuilder();
    List<Process> tunnelProcesses = new ArrayList<>();

    String delim = "";
    for (Pair<InetSocketAddress, Process> pair : ret) {
      // Join the list of String with comma as delim
      if (pair.first != null) {
        connectionStringBuilder.append(delim).
            append(pair.first.getHostName()).append(":").append(pair.first.getPort());
        delim = ",";

        // If tunneled
        if (pair.second != null) {
          tunnelProcesses.add(pair.second);
        }
      }
    }

    String newConnectionString = connectionStringBuilder.toString();
    return new Pair<String, List<Process>>(newConnectionString, tunnelProcesses);
  }