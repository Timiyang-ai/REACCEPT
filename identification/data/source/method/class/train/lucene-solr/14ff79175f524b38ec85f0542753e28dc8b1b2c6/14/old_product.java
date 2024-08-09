@Override
  public synchronized void init(PluginInfo pluginInfo) {
    super.init(pluginInfo);
    if (!enabled) {
      log.info("JMX monitoring disabled for registry " + registryName);
      return;
    }
    log.info("Initializing for registry " + registryName);
    if (serviceUrl != null && agentId != null) {
      mBeanServer = JmxUtil.findFirstMBeanServer();
      log.warn("No more than one of serviceUrl(%s) and agentId(%s) should be configured, using first MBeanServer instead of configuration.",
          serviceUrl, agentId, mBeanServer);
    } else if (serviceUrl != null) {
      try {
        mBeanServer = JmxUtil.findMBeanServerForServiceUrl(serviceUrl);
      } catch (IOException e) {
        log.warn("findMBeanServerForServiceUrl(%s) exception: %s", serviceUrl, e);
        mBeanServer = null;
      }
    } else if (agentId != null) {
      mBeanServer = JmxUtil.findMBeanServerForAgentId(agentId);
    } else {
      mBeanServer = JmxUtil.findFirstMBeanServer();
      log.debug("No serviceUrl or agentId was configured, using first MBeanServer: " + mBeanServer);
    }

    if (mBeanServer == null) {
      log.warn("No JMX server found. Not exposing Solr metrics via JMX.");
      return;
    }

    JmxObjectNameFactory jmxObjectNameFactory = new JmxObjectNameFactory(pluginInfo.name, domain);
    registry = metricManager.registry(registryName);
    // filter out MetricsMap gauges - we have a better way of handling them
    MetricFilter filter = (name, metric) -> !(metric instanceof MetricsMap);

    reporter = JmxReporter.forRegistry(registry)
                          .registerWith(mBeanServer)
                          .inDomain(domain)
                          .filter(filter)
                          .createsObjectNamesWith(jmxObjectNameFactory)
                          .build();
    reporter.start();
    // workaround for inability to register custom MBeans (to be available in metrics 4.0?)
    listener = new MetricsMapListener(mBeanServer, jmxObjectNameFactory);
    registry.addListener(listener);

    log.info("JMX monitoring for registry '" + registryName + "' enabled at server: " + mBeanServer);
  }