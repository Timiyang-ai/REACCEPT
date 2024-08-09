public static ServerPort startServer(AccumuloConfiguration conf, Property portHintProperty, TProcessor processor, String serverName, String threadName,
      Property portSearchProperty,
      Property minThreadProperty, Property timeBetweenThreadChecksProperty) throws UnknownHostException {
    int portHint = conf.getPort(portHintProperty);
    int minThreads = 2;
    if (minThreadProperty != null)
      minThreads = conf.getCount(minThreadProperty);
    long timeBetweenThreadChecks = 1000;
    if (timeBetweenThreadChecksProperty != null)
      timeBetweenThreadChecks = conf.getTimeInMillis(timeBetweenThreadChecksProperty);
    boolean portSearch = false;
    if (portSearchProperty != null)
      portSearch = conf.getBoolean(portSearchProperty);
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
          return TServerUtils.startTServer(port, processor, serverName, threadName, minThreads, timeBetweenThreadChecks);
        } catch (Exception ex) {
          log.info("Unable to use port " + port + ", retrying. (Thread Name = " + threadName + ")");
          UtilWaitThread.sleep(250);
        }
      }
    }
    throw new UnknownHostException("Unable to find a listen port");
  }