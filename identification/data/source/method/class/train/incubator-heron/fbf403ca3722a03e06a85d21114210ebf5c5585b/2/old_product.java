public static Pair<InetSocketAddress, Process> establishSSHTunnelIfNeeded(
      InetSocketAddress endpoint,
      String tunnelHost,
      int timeout,
      int retryCount,
      int retryIntervalMs,
      int verifyCount,
      boolean isVerbose) {
    if (NetworkUtils.isLocationReachable(endpoint, timeout, retryCount, retryIntervalMs)) {

      // Already reachable, return original endpoint directly
      return new Pair<InetSocketAddress, Process>(endpoint, null);
    } else {
      // Can not reach directly, trying to do ssh tunnel
      int localFreePort = SysUtils.getFreePort();
      InetSocketAddress newEndpoint = new InetSocketAddress(LOCAL_HOST, localFreePort);

      LOG.log(Level.FINE, "Trying to opening up tunnel to {0} from {1}",
          new Object[]{endpoint.toString(), newEndpoint.toString()});

      // Set up the tunnel process
      Process tunnelProcess = ShellUtils.establishSSHTunnelProcess(
          tunnelHost, localFreePort, endpoint.getHostString(), endpoint.getPort(), isVerbose);

      // Tunnel can take time to setup.
      // Verify whether the tunnel process is working fine.
      if (tunnelProcess != null && tunnelProcess.isAlive()
          && NetworkUtils.isLocationReachable(newEndpoint, timeout, verifyCount, retryIntervalMs)) {

        // Can reach the destination via ssh tunnel
        return new Pair<InetSocketAddress, Process>(newEndpoint, tunnelProcess);
      }
      LOG.log(Level.FINE, "Failed to opening up tunnel to {0} from {1}. Releasing process..",
          new Object[]{endpoint, newEndpoint});
      tunnelProcess.destroy();
    }

    // No way to reach the destination. Return null.
    return new Pair<InetSocketAddress, Process>(null, null);
  }