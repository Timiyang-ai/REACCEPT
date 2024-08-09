@Test
  public void testGetSplits() throws Exception {
    try (AccumuloClient client = getAccumuloClient()) {
      String table = getUniqueNames(1)[0];
      client.tableOperations().create(table);
      insertData(client, table, currentTimeMillis());

      Job job = Job.getInstance();
      AccumuloInputFormat.setInputTableName(job, table);
      AccumuloInputFormat.setClientInfo(job, getClientInfo());

      // split table
      TreeSet<Text> splitsToAdd = new TreeSet<>();
      for (int i = 0; i < 10000; i += 1000)
        splitsToAdd.add(new Text(String.format("%09d", i)));
      client.tableOperations().addSplits(table, splitsToAdd);
      sleepUninterruptibly(500, TimeUnit.MILLISECONDS); // wait for splits to be propagated

      // get splits without setting any range
      Collection<Text> actualSplits = client.tableOperations().listSplits(table);
      List<InputSplit> splits = inputFormat.getSplits(job);
      assertEquals(actualSplits.size() + 1, splits.size()); // No ranges set on the job so it'll
                                                            // start
                                                            // with -inf

      // set ranges and get splits
      List<Range> ranges = new ArrayList<>();
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

      client.tableOperations().offline(table, true);
      splits = inputFormat.getSplits(job);
      assertEquals(actualSplits.size(), splits.size());

      // auto adjust ranges
      ranges = new ArrayList<>();
      for (int i = 0; i < 5; i++)
        // overlapping ranges
        ranges.add(new Range(String.format("%09d", i), String.format("%09d", i + 2)));
      AccumuloInputFormat.setRanges(job, ranges);
      splits = inputFormat.getSplits(job);
      assertEquals(2, splits.size());

      AccumuloInputFormat.setAutoAdjustRanges(job, false);
      splits = inputFormat.getSplits(job);
      assertEquals(ranges.size(), splits.size());

      // BatchScan not available for offline scans
      AccumuloInputFormat.setBatchScan(job, true);
      // Reset auto-adjust ranges too
      AccumuloInputFormat.setAutoAdjustRanges(job, true);

      AccumuloInputFormat.setOfflineTableScan(job, true);
      try {
        inputFormat.getSplits(job);
        fail("An exception should have been thrown");
      } catch (IllegalArgumentException e) {}

      client.tableOperations().online(table, true);
      AccumuloInputFormat.setOfflineTableScan(job, false);

      // test for resumption of success
      splits = inputFormat.getSplits(job);
      assertEquals(2, splits.size());

      // BatchScan not available with isolated iterators
      AccumuloInputFormat.setScanIsolation(job, true);
      try {
        inputFormat.getSplits(job);
        fail("An exception should have been thrown");
      } catch (IllegalArgumentException e) {}
      AccumuloInputFormat.setScanIsolation(job, false);

      // test for resumption of success
      splits = inputFormat.getSplits(job);
      assertEquals(2, splits.size());

      // BatchScan not available with local iterators
      AccumuloInputFormat.setLocalIterators(job, true);
      try {
        inputFormat.getSplits(job);
        fail("An exception should have been thrown");
      } catch (IllegalArgumentException e) {}
      AccumuloInputFormat.setLocalIterators(job, false);

      // Check we are getting back correct type pf split
      client.tableOperations().online(table);
      splits = inputFormat.getSplits(job);
      for (InputSplit split : splits)
        assert (split instanceof BatchInputSplit);

      // We should divide along the tablet lines similar to when using `setAutoAdjustRanges(job,
      // true)`
      assertEquals(2, splits.size());
    }
  }