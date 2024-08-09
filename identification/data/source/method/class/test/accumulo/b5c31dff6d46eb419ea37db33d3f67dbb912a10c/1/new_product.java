public static ServerAddress startServer(AccumuloConfiguration conf, String address, Property portHintProperty, TProcessor processor, String serverName, String threadName,
      Property portSearchProperty,
      Property minThreadProperty, 
      Property timeBetweenThreadChecksProperty, 
      Property maxMessageSizeProperty) throws UnknownHostException {
    int portHint = conf.getPort(portHintProperty);
    int minThreads = 2;
    if (minThreadProperty != null)
      minThreads = conf.getCount(minThreadProperty);
    long timeBetweenThreadChecks = 1000;
    if (timeBetweenThreadChecksProperty != null)
      timeBetweenThreadChecks = conf.getTimeInMillis(timeBetweenThreadChecksProperty);
    long maxMessageSize = 10 * 1000 * 1000;
    if (maxMessageSizeProperty != null)
      maxMessageSize = conf.getMemoryInBytes(maxMessageSizeProperty);
    boolean portSearch = false;
    if (portSearchProperty != null)
      portSearch = conf.getBoolean(portSearchProperty);
    // create the TimedProcessor outside the port search loop so we don't try to register the same metrics mbean more than once
    TServerUtils.TimedProcessor timedProcessor = new TServerUtils.TimedProcessor(processor, serverName, threadName);
    Random random = new Random();
    for (int j = 0; j < 100; j++) {
      
      // Are we going to slide around, looking for an open port?
      int portsToSearch = 1;
      if (portSearch)
        portsToSearch = 1000;
      
      for (int i = 0; i < portsToSearch; i++) {
        int port = portHint + i;
        if (portHint == 0)
          port = 1024 + random.nextInt(65535 - 1024);
        if (port > 65535)
          port = 1024 + port % (65535 - 1024);
        try {
          InetSocketAddress addr = new InetSocketAddress(address, port);
          return TServerUtils.startTServer(addr, timedProcessor, serverName, threadName, minThreads, timeBetweenThreadChecks, maxMessageSize);
        } catch (Exception ex) {
          log.info("Unable to use port " + port + ", retrying. (Thread Name = " + threadName + ")");
          UtilWaitThread.sleep(250);
        }
      }
    }
    throw new UnknownHostException("Unable to find a listen port");
  }