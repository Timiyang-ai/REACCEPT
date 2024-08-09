@Override
   public void close()
   {
      registry.remove(MetricRegistry.name(poolName, METRIC_CATEGORY, METRIC_NAME_WAIT));
      registry.remove(MetricRegistry.name(poolName, METRIC_CATEGORY, METRIC_NAME_USAGE));
      registry.remove(MetricRegistry.name(poolName, METRIC_CATEGORY, METRIC_NAME_CONNECT));
      registry.remove(MetricRegistry.name(poolName, METRIC_CATEGORY, METRIC_NAME_TIMEOUT_RATE));
      registry.remove(MetricRegistry.name(poolName, METRIC_CATEGORY, METRIC_NAME_TOTAL_CONNECTIONS));
      registry.remove(MetricRegistry.name(poolName, METRIC_CATEGORY, METRIC_NAME_IDLE_CONNECTIONS));
      registry.remove(MetricRegistry.name(poolName, METRIC_CATEGORY, METRIC_NAME_ACTIVE_CONNECTIONS));
      registry.remove(MetricRegistry.name(poolName, METRIC_CATEGORY, METRIC_NAME_PENDING_CONNECTIONS));
      registry.remove(MetricRegistry.name(poolName, METRIC_CATEGORY, METRIC_NAME_MAX_CONNECTIONS));
      registry.remove(MetricRegistry.name(poolName, METRIC_CATEGORY, METRIC_NAME_MIN_CONNECTIONS));
   }