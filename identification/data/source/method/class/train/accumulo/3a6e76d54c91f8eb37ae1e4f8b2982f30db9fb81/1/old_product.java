private static void flushAll(final ClientContext context)
      throws AccumuloException, AccumuloSecurityException {

    final AtomicInteger flushesStarted = new AtomicInteger(0);

    Runnable flushTask = new Runnable() {

      @Override
      public void run() {
        try {
          Connector conn = context.getConnector();
          Set<String> tables = conn.tableOperations().tableIdMap().keySet();
          for (String table : tables) {
            if (table.equals(MetadataTable.NAME))
              continue;
            try {
              conn.tableOperations().flush(table, null, null, false);
              flushesStarted.incrementAndGet();
            } catch (TableNotFoundException e) {
              // ignore
            }
          }
        } catch (Exception e) {
          log.warn("Failed to intiate flush {}", e.getMessage());
        }
      }
    };

    Thread flusher = new Thread(flushTask);
    flusher.setDaemon(true);
    flusher.start();

    long start = System.currentTimeMillis();
    try {
      flusher.join(3000);
    } catch (InterruptedException e) {
      // ignore
    }

    while (flusher.isAlive() && System.currentTimeMillis() - start < 15000) {
      int flushCount = flushesStarted.get();
      try {
        flusher.join(1000);
      } catch (InterruptedException e) {
        // ignore
      }

      if (flushCount == flushesStarted.get()) {
        // no progress was made while waiting for join... maybe its stuck, stop waiting on it
        break;
      }
    }
  }