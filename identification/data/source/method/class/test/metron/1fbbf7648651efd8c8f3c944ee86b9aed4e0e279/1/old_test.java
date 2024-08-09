@Test
  public void testWrite() throws Exception {

    // add a tuple to the batch
    byte[] rowKey1 = mapper.rowKey(tuple1);
    ColumnList cols1 = mapper.columns(tuple1);
    List<Mutation> mutations1 = client.constructMutationReq(rowKey1, cols1, Durability.SYNC_WAL);
    client.batchMutate(mutations1);

    HBaseProjectionCriteria criteria = new HBaseProjectionCriteria();
    criteria.addColumnFamily(WidgetMapper.CF_STRING);

    // read back the tuple
    Get get1 = client.constructGetRequests(rowKey1, criteria);
    Result[] results = client.batchGet(Arrays.asList(get1));
    Assert.assertEquals(1, results.length);

    // validate
    assertEquals(1, results.length);
    assertEquals(widget1, toWidget(results[0]));
  }