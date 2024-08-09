@Override
  public InputSplit[] getSplits(JobConf job, int numSplits) throws IOException {
    Level logLevel = getLogLevel(job);
    log.setLevel(logLevel);
    validateOptions(job);

    Random random = new Random();
    LinkedList<InputSplit> splits = new LinkedList<InputSplit>();
    Map<String,InputTableConfig> tableConfigs = getInputTableConfigs(job);
    for (Map.Entry<String,InputTableConfig> tableConfigEntry : tableConfigs.entrySet()) {
      String tableName = tableConfigEntry.getKey();
      InputTableConfig tableConfig = tableConfigEntry.getValue();

      Instance instance = getInstance(job);
      boolean mockInstance;
      String tableId;
      // resolve table name to id once, and use id from this point forward
      if (instance instanceof MockInstance) {
        tableId = "";
        mockInstance = true;
      } else {
        try {
          tableId = Tables.getTableId(instance, tableName);
        } catch (TableNotFoundException e) {
          throw new IOException(e);
        }
        mockInstance = false;
      }

      Authorizations auths = getScanAuthorizations(job);
      String principal = getPrincipal(job);
      AuthenticationToken token = getAuthenticationToken(job);

      boolean autoAdjust = tableConfig.shouldAutoAdjustRanges();
      List<Range> ranges = autoAdjust ? Range.mergeOverlapping(tableConfig.getRanges()) : tableConfig.getRanges();
      if (ranges.isEmpty()) {
        ranges = new ArrayList<Range>(1);
        ranges.add(new Range());
      }

      // get the metadata information for these ranges
      Map<String,Map<KeyExtent,List<Range>>> binnedRanges = new HashMap<String,Map<KeyExtent,List<Range>>>();
      TabletLocator tl;
      try {
        if (tableConfig.isOfflineScan()) {
          binnedRanges = binOfflineTable(job, tableId, ranges);
          while (binnedRanges == null) {
            // Some tablets were still online, try again
            UtilWaitThread.sleep(100 + random.nextInt(100)); // sleep randomly between 100 and 200 ms
            binnedRanges = binOfflineTable(job, tableId, ranges);
          }
        } else {
          tl = getTabletLocator(job, tableId);
          // its possible that the cache could contain complete, but old information about a tables tablets... so clear it
          tl.invalidateCache();

          ClientContext context = new ClientContext(getInstance(job), new Credentials(getPrincipal(job), getAuthenticationToken(job)),
              getClientConfiguration(job));
          while (!tl.binRanges(context, ranges, binnedRanges).isEmpty()) {
            if (!(instance instanceof MockInstance)) {
              if (!Tables.exists(instance, tableId))
                throw new TableDeletedException(tableId);
              if (Tables.getTableState(instance, tableId) == TableState.OFFLINE)
                throw new TableOfflineException(instance, tableId);
            }
            binnedRanges.clear();
            log.warn("Unable to locate bins for specified ranges. Retrying.");
            UtilWaitThread.sleep(100 + random.nextInt(100)); // sleep randomly between 100 and 200 ms
            tl.invalidateCache();
          }
        }
      } catch (Exception e) {
        throw new IOException(e);
      }

      HashMap<Range,ArrayList<String>> splitsToAdd = null;

      if (!autoAdjust)
        splitsToAdd = new HashMap<Range,ArrayList<String>>();

      HashMap<String,String> hostNameCache = new HashMap<String,String>();
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
          for (Range r : extentRanges.getValue()) {
            if (autoAdjust) {
              // divide ranges into smaller ranges, based on the tablets
              RangeInputSplit split = new RangeInputSplit(tableName, tableId, ke.clip(r), new String[] {location});

              split.setOffline(tableConfig.isOfflineScan());
              split.setIsolatedScan(tableConfig.shouldUseIsolatedScanners());
              split.setUsesLocalIterators(tableConfig.shouldUseLocalIterators());
              split.setMockInstance(mockInstance);
              split.setFetchedColumns(tableConfig.getFetchedColumns());
              split.setPrincipal(principal);
              split.setToken(token);
              split.setInstanceName(instance.getInstanceName());
              split.setZooKeepers(instance.getZooKeepers());
              split.setAuths(auths);
              split.setIterators(tableConfig.getIterators());
              split.setLogLevel(logLevel);

              splits.add(split);
            } else {
              // don't divide ranges
              ArrayList<String> locations = splitsToAdd.get(r);
              if (locations == null)
                locations = new ArrayList<String>(1);
              locations.add(location);
              splitsToAdd.put(r, locations);
            }
          }
        }
      }

      if (!autoAdjust)
        for (Map.Entry<Range,ArrayList<String>> entry : splitsToAdd.entrySet()) {
          RangeInputSplit split = new RangeInputSplit(tableName, tableId, entry.getKey(), entry.getValue().toArray(new String[0]));

          split.setOffline(tableConfig.isOfflineScan());
          split.setIsolatedScan(tableConfig.shouldUseIsolatedScanners());
          split.setUsesLocalIterators(tableConfig.shouldUseLocalIterators());
          split.setMockInstance(mockInstance);
          split.setFetchedColumns(tableConfig.getFetchedColumns());
          split.setPrincipal(principal);
          split.setToken(token);
          split.setInstanceName(instance.getInstanceName());
          split.setZooKeepers(instance.getZooKeepers());
          split.setAuths(auths);
          split.setIterators(tableConfig.getIterators());
          split.setLogLevel(logLevel);

          splits.add(split);
        }
    }

    return splits.toArray(new InputSplit[splits.size()]);
  }