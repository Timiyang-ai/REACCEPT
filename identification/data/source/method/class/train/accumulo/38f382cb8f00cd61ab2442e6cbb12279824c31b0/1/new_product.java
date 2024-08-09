@Override
  public InputSplit[] getSplits(JobConf job, int numSplits) throws IOException {
    Level logLevel = getLogLevel(job);
    log.setLevel(logLevel);
    validateOptions(job);

    Random random = new SecureRandom();
    LinkedList<InputSplit> splits = new LinkedList<>();
    Map<String,org.apache.accumulo.core.client.mapreduce.InputTableConfig> tableConfigs = getInputTableConfigs(
        job);
    for (Map.Entry<String,org.apache.accumulo.core.client.mapreduce.InputTableConfig> tableConfigEntry : tableConfigs
        .entrySet()) {
      String tableName = tableConfigEntry.getKey();
      org.apache.accumulo.core.client.mapreduce.InputTableConfig tableConfig = tableConfigEntry
          .getValue();

      try (
          ClientContext context = new ClientContext(Configurator.getClientProperties(CLASS, job))) {
        Table.ID tableId;
        // resolve table name to id once, and use id from this point forward
        try {
          tableId = Tables.getTableId(context, tableName);
        } catch (TableNotFoundException e) {
          throw new IOException(e);
        }

        boolean batchScan = Configurator.isBatchScan(CLASS, job);
        boolean supportBatchScan = !(tableConfig.isOfflineScan()
            || tableConfig.shouldUseIsolatedScanners() || tableConfig.shouldUseLocalIterators());
        if (batchScan && !supportBatchScan)
          throw new IllegalArgumentException("BatchScanner optimization not available for offline"
              + " scan, isolated, or local iterators");

        boolean autoAdjust = tableConfig.shouldAutoAdjustRanges();
        if (batchScan && !autoAdjust)
          throw new IllegalArgumentException(
              "AutoAdjustRanges must be enabled when using BatchScanner optimization");

        List<Range> ranges = autoAdjust ? Range.mergeOverlapping(tableConfig.getRanges())
            : tableConfig.getRanges();
        if (ranges.isEmpty()) {
          ranges = new ArrayList<>(1);
          ranges.add(new Range());
        }

        // get the metadata information for these ranges
        Map<String,Map<KeyExtent,List<Range>>> binnedRanges = new HashMap<>();
        TabletLocator tl;
        try {
          if (tableConfig.isOfflineScan()) {
            binnedRanges = binOfflineTable(job, tableId, ranges);
            while (binnedRanges == null) {
              // Some tablets were still online, try again
              // sleep randomly between 100 and 200 ms
              sleepUninterruptibly(100 + random.nextInt(100), TimeUnit.MILLISECONDS);
              binnedRanges = binOfflineTable(job, tableId, ranges);
            }
          } else {
            tl = TabletLocator.getLocator(context, tableId);
            // its possible that the cache could contain complete, but old information about a
            // tables tablets... so clear it
            tl.invalidateCache();

            while (!tl.binRanges(context, ranges, binnedRanges).isEmpty()) {
              String tableIdStr = tableId.canonicalID();
              if (!Tables.exists(context, tableId))
                throw new TableDeletedException(tableIdStr);
              if (Tables.getTableState(context, tableId) == TableState.OFFLINE)
                throw new TableOfflineException(Tables.getTableOfflineMsg(context, tableId));
              binnedRanges.clear();
              log.warn("Unable to locate bins for specified ranges. Retrying.");
              // sleep randomly between 100 and 200 ms
              sleepUninterruptibly(100 + random.nextInt(100), TimeUnit.MILLISECONDS);
              tl.invalidateCache();
            }
          }
        } catch (Exception e) {
          throw new IOException(e);
        }

        HashMap<Range,ArrayList<String>> splitsToAdd = null;

        if (!autoAdjust)
          splitsToAdd = new HashMap<>();

        HashMap<String,String> hostNameCache = new HashMap<>();
        for (Map.Entry<String,Map<KeyExtent,List<Range>>> tserverBin : binnedRanges.entrySet()) {
          String ip = tserverBin.getKey().split(":", 2)[0];
          String location = hostNameCache.get(ip);
          if (location == null) {
            InetAddress inetAddress = InetAddress.getByName(ip);
            location = inetAddress.getCanonicalHostName();
            hostNameCache.put(ip, location);
          }
          for (Map.Entry<KeyExtent,List<Range>> extentRanges : tserverBin.getValue().entrySet()) {
            Range ke = extentRanges.getKey().toDataRange();
            if (batchScan) {
              // group ranges by tablet to be read by a BatchScanner
              ArrayList<Range> clippedRanges = new ArrayList<>();
              for (Range r : extentRanges.getValue())
                clippedRanges.add(ke.clip(r));

              org.apache.accumulo.core.clientImpl.mapred.BatchInputSplit split = new org.apache.accumulo.core.clientImpl.mapred.BatchInputSplit(
                  tableName, tableId, clippedRanges, new String[] {location});
              org.apache.accumulo.core.clientImpl.mapreduce.SplitUtils.updateSplit(split,
                  tableConfig, logLevel);

              splits.add(split);
            } else {
              // not grouping by tablet
              for (Range r : extentRanges.getValue()) {
                if (autoAdjust) {
                  // divide ranges into smaller ranges, based on the tablets
                  RangeInputSplit split = new RangeInputSplit(tableName, tableId.canonicalID(),
                      ke.clip(r), new String[] {location});
                  org.apache.accumulo.core.clientImpl.mapreduce.SplitUtils.updateSplit(split,
                      tableConfig, logLevel);
                  split.setOffline(tableConfig.isOfflineScan());
                  split.setIsolatedScan(tableConfig.shouldUseIsolatedScanners());
                  split.setUsesLocalIterators(tableConfig.shouldUseLocalIterators());

                  splits.add(split);
                } else {
                  // don't divide ranges
                  ArrayList<String> locations = splitsToAdd.get(r);
                  if (locations == null)
                    locations = new ArrayList<>(1);
                  locations.add(location);
                  splitsToAdd.put(r, locations);
                }
              }
            }
          }
        }

        if (!autoAdjust)
          for (Map.Entry<Range,ArrayList<String>> entry : splitsToAdd.entrySet()) {
            RangeInputSplit split = new RangeInputSplit(tableName, tableId.canonicalID(),
                entry.getKey(), entry.getValue().toArray(new String[0]));
            org.apache.accumulo.core.clientImpl.mapreduce.SplitUtils.updateSplit(split, tableConfig,
                logLevel);
            split.setOffline(tableConfig.isOfflineScan());
            split.setIsolatedScan(tableConfig.shouldUseIsolatedScanners());
            split.setUsesLocalIterators(tableConfig.shouldUseLocalIterators());

            splits.add(split);
          }
      }
    }

    return splits.toArray(new InputSplit[splits.size()]);
  }