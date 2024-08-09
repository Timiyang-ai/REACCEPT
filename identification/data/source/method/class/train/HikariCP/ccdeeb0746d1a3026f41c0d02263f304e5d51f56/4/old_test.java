   @Test
   public void close()
   {
      testee.close();

      verify(mockMetricRegistry).remove("mypool.pool.Wait");
      verify(mockMetricRegistry).remove("mypool.pool.Usage");
      verify(mockMetricRegistry).remove("mypool.pool.ConnectionCreation");
      verify(mockMetricRegistry).remove("mypool.pool.ConnectionTimeoutRate");
      verify(mockMetricRegistry).remove("mypool.pool.TotalConnections");
      verify(mockMetricRegistry).remove("mypool.pool.IdleConnections");
      verify(mockMetricRegistry).remove("mypool.pool.ActiveConnections");
      verify(mockMetricRegistry).remove("mypool.pool.PendingConnections");
      verify(mockMetricRegistry).remove("mypool.pool.MaxConnections");
      verify(mockMetricRegistry).remove("mypool.pool.MinConnections");
   }