@Override
  public synchronized void init(PluginInfo pluginInfo) {
    super.init(pluginInfo);
    if (!enabled) {
      log.info("Reporter disabled for registry " + registryName);
      return;
    }
    log.debug("Initializing for registry " + registryName);
    if (serviceUrl != null && agentId != null) {
      mBeanServer = JmxUtil.findFirstMBeanServer();
      log.warn("No more than one of serviceUrl({}) and agentId({}) should be configured, using first MBeanServer instead of configuration.",
          serviceUrl, agentId, mBeanServer);
    } else if (serviceUrl != null) {
      // reuse existing services
      mBeanServer = serviceRegistry.getOrCreate(serviceUrl, () -> JmxUtil.findMBeanServerForServiceUrl(serviceUrl));
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

    if (domain == null || domain.isEmpty()) {
      domain = registryName;
    }
    String fullDomain = domain;
    if (rootName != null && !rootName.isEmpty()) {
      fullDomain = rootName + "." + domain;
    }
    JmxObjectNameFactory jmxObjectNameFactory = new JmxObjectNameFactory(pluginInfo.name, fullDomain);
    registry = metricManager.registry(registryName);
    MetricFilter filter;
    if (filters.isEmpty()) {
      filter = MetricFilter.ALL;
    } else {
      filter = new SolrMetricManager.PrefixFilter(filters);
    }

    String tag = Integer.toHexString(this.hashCode());
    reporter = JmxMetricsReporter.forRegistry(registry)
                          .registerWith(mBeanServer)
                          .inDomain(fullDomain)
                          .filter(filter)
                          .createsObjectNamesWith(jmxObjectNameFactory)
                          .withTag(tag)
                          .build();
    reporter.start();
    started = true;
    log.info("JMX monitoring for '" + fullDomain + "' (registry '" + registryName + "') enabled at server: " + mBeanServer);
  }