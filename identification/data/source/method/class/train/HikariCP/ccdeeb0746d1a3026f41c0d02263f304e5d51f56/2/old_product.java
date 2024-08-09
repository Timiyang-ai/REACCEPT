@Override
   public void close()
   {
      final String poolName = pool.getConfiguration().getPoolName();
      registry.remove(MetricRegistry.name(poolName, "pool", "Wait"));
      registry.remove(MetricRegistry.name(poolName, "pool", "Usage"));
      registry.remove(MetricRegistry.name(poolName, "pool", "TotalConnections"));
      registry.remove(MetricRegistry.name(poolName, "pool", "IdleConnections"));
      registry.remove(MetricRegistry.name(poolName, "pool", "ActiveConnections"));
      registry.remove(MetricRegistry.name(poolName, "pool", "PendingConnections"));
   }