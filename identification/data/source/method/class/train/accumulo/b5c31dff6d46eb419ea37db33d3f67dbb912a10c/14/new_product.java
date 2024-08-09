public static ServerAddress startServer(AccumuloServerContext service, String hostname, Property portHintProperty, TProcessor processor, String serverName,
      String threadName, Property portSearchProperty, Property minThreadProperty, Property timeBetweenThreadChecksProperty, Property maxMessageSizeProperty)
      throws UnknownHostException {
    final AccumuloConfiguration config = service.getConfiguration();

    final int portHint = config.getPort(portHintProperty);

    int minThreads = 2;
    if (minThreadProperty != null)
      minThreads = config.getCount(minThreadProperty);

    long timeBetweenThreadChecks = 1000;
    if (timeBetweenThreadChecksProperty != null)
      timeBetweenThreadChecks = config.getTimeInMillis(timeBetweenThreadChecksProperty);

    long maxMessageSize = 10 * 1000 * 1000;
    if (maxMessageSizeProperty != null)
      maxMessageSize = config.getMemoryInBytes(maxMessageSizeProperty);

    boolean portSearch = false;
    if (portSearchProperty != null)
      portSearch = config.getBoolean(portSearchProperty);

    final int simpleTimerThreadpoolSize = config.getCount(Property.GENERAL_SIMPLETIMER_THREADPOOL_SIZE);

    // create the TimedProcessor outside the port search loop so we don't try to register the same metrics mbean more than once
    TimedProcessor timedProcessor = new TimedProcessor(config, processor, serverName, threadName);

    Random random = new Random();
    for (int j = 0; j < 100; j++) {

      // Are we going to slide around, looking for an open port?
      int portsToSearch = 1;
      if (portSearch)
        portsToSearch = 1000;

      for (int i = 0; i < portsToSearch; i++) {
        int port = portHint + i;
        if (portHint != 0 && i > 0)
          port = 1024 + random.nextInt(65535 - 1024);
        if (port > 65535)
          port = 1024 + port % (65535 - 1024);
        try {
          HostAndPort addr = HostAndPort.fromParts(hostname, port);
          return TServerUtils.startTServer(addr, timedProcessor, serverName, threadName, minThreads,
              simpleTimerThreadpoolSize, timeBetweenThreadChecks, maxMessageSize,
              service.getServerSslParams(), service.getClientTimeoutInMillis());
        } catch (TTransportException ex) {
          log.error("Unable to start TServer", ex);
          if (ex.getCause() == null || ex.getCause().getClass() == BindException.class) {
            // Note: with a TNonblockingServerSocket a "port taken" exception is a cause-less
            // TTransportException, and with a TSocket created by TSSLTransportFactory, it
            // comes through as caused by a BindException.
            log.info("Unable to use port {}, retrying. (Thread Name = {})", port, threadName);
            UtilWaitThread.sleep(250);
          } else {
            // thrift is passing up a nested exception that isn't a BindException,
            // so no reason to believe retrying on a different port would help.
            log.error("Unable to start TServer", ex);
            break;
          }
        }
      }
    }
    throw new UnknownHostException("Unable to find a listen port");
  }