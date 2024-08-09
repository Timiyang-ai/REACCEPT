@Override
   public void close()
   {
      registry.remove(MetricRegistry.name(pool.getConfiguration().getPoolName(), "pool", "Wait"));
      registry.remove(MetricRegistry.name(pool.getConfiguration().getPoolName(), "pool", "Usage"));
      registry.remove(MetricRegistry.name(pool.getConfiguration().getPoolName(), "pool", "TotalConnections"));
      registry.remove(MetricRegistry.name(pool.getConfiguration().getPoolName(), "pool", "IdleConnections"));
      registry.remove(MetricRegistry.name(pool.getConfiguration().getPoolName(), "pool", "ActiveConnections"));
      registry.remove(MetricRegistry.name(pool.getConfiguration().getPoolName(), "pool", "PendingConnections"));
   }