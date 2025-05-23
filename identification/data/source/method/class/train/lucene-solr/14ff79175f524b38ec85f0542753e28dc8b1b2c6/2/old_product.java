@Override
  public synchronized void init(PluginInfo pluginInfo) {
    super.init(pluginInfo);

    if (serviceUrl != null && agentId != null) {
      ManagementFactory.getPlatformMBeanServer(); // Ensure at least one MBeanServer is available.
      mBeanServer = JmxUtil.findFirstMBeanServer();
      log.warn("No more than one of serviceUrl(%s) and agentId(%s) should be configured, using first MBeanServer instead of configuration.",
          serviceUrl, agentId, mBeanServer);
    }
    else if (serviceUrl != null) {
      try {
        mBeanServer = JmxUtil.findMBeanServerForServiceUrl(serviceUrl);
      } catch (IOException e) {
        log.warn("findMBeanServerForServiceUrl(%s) exception: %s", serviceUrl, e);
        mBeanServer = null;
      }
    }
    else if (agentId != null) {
      mBeanServer = JmxUtil.findMBeanServerForAgentId(agentId);
    } else {
      ManagementFactory.getPlatformMBeanServer(); // Ensure at least one MBeanServer is available.
      mBeanServer = JmxUtil.findFirstMBeanServer();
      log.warn("No serviceUrl or agentId was configured, using first MBeanServer.", mBeanServer);
    }

    if (mBeanServer == null) {
      log.warn("No JMX server found. Not exposing Solr metrics.");
      return;
    }

    JmxObjectNameFactory jmxObjectNameFactory = new JmxObjectNameFactory(pluginInfo.name, domain);

    reporter = JmxReporter.forRegistry(metricManager.registry(registryName))
                          .registerWith(mBeanServer)
                          .inDomain(domain)
                          .createsObjectNamesWith(jmxObjectNameFactory)
                          .build();
    reporter.start();

    log.info("JMX monitoring enabled at server: " + mBeanServer);
  }