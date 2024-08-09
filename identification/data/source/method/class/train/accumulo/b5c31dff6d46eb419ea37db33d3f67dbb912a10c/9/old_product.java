public static ServerAddress startServer(AccumuloServerContext service, String hostname,
      Property portHintProperty, TProcessor processor, String serverName, String threadName,
      Property portSearchProperty, Property minThreadProperty,
      Property timeBetweenThreadChecksProperty, Property maxMessageSizeProperty)
      throws UnknownHostException {
    final AccumuloConfiguration config = service.getConfiguration();

    final int[] portHint = config.getPort(portHintProperty);

    int minThreads = 2;
    if (minThreadProperty != null)
      minThreads = config.getCount(minThreadProperty);

    long timeBetweenThreadChecks = 1000;
    if (timeBetweenThreadChecksProperty != null)
      timeBetweenThreadChecks = config.getTimeInMillis(timeBetweenThreadChecksProperty);

    long maxMessageSize = 10 * 1000 * 1000;
    if (maxMessageSizeProperty != null)
      maxMessageSize = config.getAsBytes(maxMessageSizeProperty);

    boolean portSearch = false;
    if (portSearchProperty != null)
      portSearch = config.getBoolean(portSearchProperty);

    final int simpleTimerThreadpoolSize = config
        .getCount(Property.GENERAL_SIMPLETIMER_THREADPOOL_SIZE);
    final ThriftServerType serverType = service.getThriftServerType();

    if (ThriftServerType.SASL == serverType) {
      processor = updateSaslProcessor(serverType, processor);
    }

    // create the TimedProcessor outside the port search loop so we don't try to register the same
    // metrics mbean more than once
    TimedProcessor timedProcessor = new TimedProcessor(config, processor, serverName, threadName);

    HostAndPort[] addresses = getHostAndPorts(hostname, portHint);
    try {
      return TServerUtils.startTServer(serverType, timedProcessor, serverName, threadName,
          minThreads, simpleTimerThreadpoolSize, timeBetweenThreadChecks, maxMessageSize,
          service.getServerSslParams(), service.getSaslParams(), service.getClientTimeoutInMillis(),
          addresses);
    } catch (TTransportException e) {
      if (portSearch) {
        HostAndPort last = addresses[addresses.length - 1];
        // Attempt to allocate a port outside of the specified port property
        // Search sequentially over the next 1000 ports
        for (int i = last.getPort() + 1; i < last.getPort() + 1001; i++) {
          int port = i;
          if (port > 65535) {
            break;
          }
          try {
            HostAndPort addr = HostAndPort.fromParts(hostname, port);
            return TServerUtils.startTServer(serverType, timedProcessor, serverName, threadName,
                minThreads, simpleTimerThreadpoolSize, timeBetweenThreadChecks, maxMessageSize,
                service.getServerSslParams(), service.getSaslParams(),
                service.getClientTimeoutInMillis(), addr);
          } catch (TTransportException tte) {
            log.info("Unable to use port {}, retrying. (Thread Name = {})", port, threadName);
          }
        }
        log.error("Unable to start TServer", e);
        throw new UnknownHostException("Unable to find a listen port");
      } else {
        log.error("Unable to start TServer", e);
        throw new UnknownHostException("Unable to find a listen port");
      }
    }
  }