@Test
  public void testGetSplits() throws Exception {
    Connector conn = getConnector();
    String table = getUniqueNames(1)[0];
    conn.tableOperations().create(table);
    insertData(table, currentTimeMillis());

    ClientConfiguration clientConf = cluster.getClientConfig();
    AccumuloConfiguration clusterClientConf = new ConfigurationCopy(new DefaultConfiguration());

    // Pass SSL and CredentialProvider options into the ClientConfiguration given to AccumuloInputFormat
    boolean sslEnabled = Boolean.valueOf(clusterClientConf.get(Property.INSTANCE_RPC_SSL_ENABLED));
    if (sslEnabled) {
      ClientProperty[] sslProperties = new ClientProperty[] {ClientProperty.INSTANCE_RPC_SSL_ENABLED, ClientProperty.INSTANCE_RPC_SSL_CLIENT_AUTH,
          ClientProperty.RPC_SSL_KEYSTORE_PATH, ClientProperty.RPC_SSL_KEYSTORE_TYPE, ClientProperty.RPC_SSL_KEYSTORE_PASSWORD,
          ClientProperty.RPC_SSL_TRUSTSTORE_PATH, ClientProperty.RPC_SSL_TRUSTSTORE_TYPE, ClientProperty.RPC_SSL_TRUSTSTORE_PASSWORD,
          ClientProperty.RPC_USE_JSSE, ClientProperty.GENERAL_SECURITY_CREDENTIAL_PROVIDER_PATHS};

      for (ClientProperty prop : sslProperties) {
        // The default property is returned if it's not in the ClientConfiguration so we don't have to check if the value is actually defined
        clientConf.setProperty(prop, clusterClientConf.get(prop.getAccumuloProperty()));
      }
    }

    Job job = Job.getInstance();
    AccumuloInputFormat.setInputTableName(job, table);
    AccumuloInputFormat.setZooKeeperInstance(job, clientConf);
    AccumuloInputFormat.setConnectorInfo(job, getAdminPrincipal(), getAdminToken());

    // split table
    TreeSet<Text> splitsToAdd = new TreeSet<Text>();
    for (int i = 0; i < 10000; i += 1000)
      splitsToAdd.add(new Text(String.format("%09d", i)));
    conn.tableOperations().addSplits(table, splitsToAdd);
    UtilWaitThread.sleep(500); // wait for splits to be propagated

    // get splits without setting any range
    Collection<Text> actualSplits = conn.tableOperations().listSplits(table);
    List<InputSplit> splits = inputFormat.getSplits(job);
    assertEquals(actualSplits.size() + 1, splits.size()); // No ranges set on the job so it'll start with -inf

    // set ranges and get splits
    List<Range> ranges = new ArrayList<Range>();
    for (Text text : actualSplits)
      ranges.add(new Range(text));
    AccumuloInputFormat.setRanges(job, ranges);
    splits = inputFormat.getSplits(job);
    assertEquals(actualSplits.size(), splits.size());

    // offline mode
    AccumuloInputFormat.setOfflineTableScan(job, true);
    try {
      inputFormat.getSplits(job);
      fail("An exception should have been thrown");
    } catch (IOException e) {}

    conn.tableOperations().offline(table);
    splits = inputFormat.getSplits(job);
    assertEquals(actualSplits.size(), splits.size());

    // auto adjust ranges
    ranges = new ArrayList<Range>();
    for (int i = 0; i < 5; i++)
      // overlapping ranges
      ranges.add(new Range(String.format("%09d", i), String.format("%09d", i + 2)));
    AccumuloInputFormat.setRanges(job, ranges);
    splits = inputFormat.getSplits(job);
    assertEquals(2, splits.size());

    AccumuloInputFormat.setAutoAdjustRanges(job, false);
    splits = inputFormat.getSplits(job);
    assertEquals(ranges.size(), splits.size());

    //BatchScan not available for offline scans
    AccumuloInputFormat.setBatchScan(job, true);

    AccumuloInputFormat.setOfflineTableScan(job, true);
    try {
      inputFormat.getSplits(job);
      fail("An exception should have been thrown");
    } catch (IOException e) {}
    AccumuloInputFormat.setOfflineTableScan(job, false);

    // test for resumption of success
    inputFormat.getSplits(job);
    assertEquals(2, splits.size());

    //BatchScan not available with isolated iterators
    AccumuloInputFormat.setScanIsolation(job, true);
    try {
      inputFormat.getSplits(job);
      fail("An exception should have been thrown");
    } catch (IOException e) {}
    AccumuloInputFormat.setScanIsolation(job, false);

    // test for resumption of success
    inputFormat.getSplits(job);
    assertEquals(2, splits.size());

    //BatchScan not available with local iterators
    AccumuloInputFormat.setLocalIterators(job, true);
    try {
      inputFormat.getSplits(job);
      fail("An exception should have been thrown");
    } catch (IOException e) {}
    AccumuloInputFormat.setLocalIterators(job, false);

    //Check we are getting back correct type pf split
    conn.tableOperations().online(table);
    splits = inputFormat.getSplits(job);
    for (InputSplit split: splits)
      assert(split instanceof BatchInputSplit);

    //We should divide along the tablet lines similar to when using `setAutoAdjustRanges(job, true)`
    assertEquals(2, splits.size());
  }