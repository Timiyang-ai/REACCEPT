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
      synchronized (serviceRegistry) {
        mBeanServer = serviceRegistry.get(serviceUrl);
        if (mBeanServer == null) {
          try {
            mBeanServer = JmxUtil.findMBeanServerForServiceUrl(serviceUrl);
            if (mBeanServer != null) {
              serviceRegistry.register(serviceUrl, mBeanServer);
            }
          } catch (IOException e) {
            log.warn("findMBeanServerForServiceUrl({}) exception: {}", serviceUrl, e);
            mBeanServer = null;
          }
        }
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

    if (domain == null || domain.isEmpty()) {
      domain = registryName;
    }
    String fullDomain = domain;
    if (rootName != null && !rootName.isEmpty()) {
      fullDomain = rootName + "." + domain;
    }
    JmxObjectNameFactory jmxObjectNameFactory = new JmxObjectNameFactory(pluginInfo.name, fullDomain);
    registry = metricManager.registry(registryName);
    // filter out MetricsMap gauges - we have a better way of handling them
    MetricFilter mmFilter = (name, metric) -> !(metric instanceof MetricsMap);
    MetricFilter filter;
    if (filters.isEmpty()) {
      filter = mmFilter;
    } else {
      // apply also prefix filters
      SolrMetricManager.PrefixFilter prefixFilter = new SolrMetricManager.PrefixFilter(filters);
      filter = new SolrMetricManager.AndFilter(prefixFilter, mmFilter);
    }

    reporter = JmxReporter.forRegistry(registry)
                          .registerWith(mBeanServer)
                          .inDomain(fullDomain)
                          .filter(filter)
                          .createsObjectNamesWith(jmxObjectNameFactory)
                          .build();
    reporter.start();
    // workaround for inability to register custom MBeans (to be available in metrics 4.0?)
    listener = new MetricsMapListener(mBeanServer, jmxObjectNameFactory);
    registry.addListener(listener);

    log.info("JMX monitoring for '" + fullDomain + "' (registry '" + registryName + "') enabled at server: " + mBeanServer);
  }