@Test
  public void testWrite() throws Exception {

    // add a tuple to the batch
    client.addMutation(rowKey1, cols1, Durability.SYNC_WAL);
    client.mutate();

    HBaseProjectionCriteria criteria = new HBaseProjectionCriteria();
    criteria.addColumnFamily(WidgetMapper.CF_STRING);

    // read back the tuple
    client.addGet(rowKey1, criteria);
    Result[] results = client.getAll();
    Assert.assertEquals(1, results.length);

    // validate
    assertEquals(1, results.length);
    assertEquals(widget1, toWidget(results[0]));
  }