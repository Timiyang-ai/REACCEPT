@Override
    public void run() {

      latch.countDown();
      try {
        latch.await();
      } catch (InterruptedException e) {
        log.error("Thread interrupted with id {}", workerIndex);
        Thread.currentThread().interrupt();
        return;
      }

      log.debug("Creating scanner in worker thread {}", workerIndex);

      try {

        scanner = accumuloClient.createScanner(tablename, new Authorizations());

        // Never start readahead
        scanner.setReadaheadThreshold(Long.MAX_VALUE);
        scanner.setBatchSize(1);

        // create different ranges to try to hit more than one tablet.
        scanner.setRange(new Range(new Text(Integer.toString(workerIndex)), new Text("9")));

        scanner.fetchColumnFamily(new Text("fam1"));

        for (Map.Entry<Key,Value> entry : scanner) {

          // exit when success condition is met.
          if (!testInProgress.get()) {
            scanner.clearScanIterators();
            scanner.close();
            return;
          }

          Text row = entry.getKey().getRow();

          log.debug("worker {}, row {}", workerIndex, row);

          if (entry.getValue() != null) {

            Value prevValue = resultsByWorker.put(workerIndex, entry.getValue());

            // value should always being increasing
            if (prevValue != null) {

              log.trace("worker {} values {}", workerIndex,
                  String.format("%1$s < %2$s", prevValue, entry.getValue()));

              assertTrue(prevValue.compareTo(entry.getValue()) > 0);
            }
          } else {
            log.info("Scanner returned null");
            fail("Scanner returned unexpected null value");
          }

        }
        log.debug("Scanner ran out of data. (info only, not an error) ");
      } catch (TableNotFoundException e) {
        throw new IllegalStateException("Initialization failure. Could not create scanner", e);
      } finally {
        if (scanner != null) {
          scanner.close();
        }
      }
    }