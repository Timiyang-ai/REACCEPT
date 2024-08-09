@Override
   public void close()
   {
      registry.remove(MetricRegistry.name(poolName, "pool", "Wait"));
      registry.remove(MetricRegistry.name(poolName, "pool", "Usage"));
      registry.remove(MetricRegistry.name(poolName, "pool", "TotalConnections"));
      registry.remove(MetricRegistry.name(poolName, "pool", "IdleConnections"));
      registry.remove(MetricRegistry.name(poolName, "pool", "ActiveConnections"));
      registry.remove(MetricRegistry.name(poolName, "pool", "PendingConnections"));
   }