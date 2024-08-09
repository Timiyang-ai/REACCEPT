@Test
  public void testGet() throws IOException, InterruptedException {
    // Testing simple get
    byte[] key = new byte[] {123, 124, 122};
    byte[] distributedKey = keyDistributor.getDistributedKey(key);
    byte[] value = Bytes.toBytes("some");

    hTable.put(new Put(distributedKey).add(CF, QUAL, value));

    Result result = hTable.get(new Get(distributedKey));
    Assert.assertArrayEquals(key, keyDistributor.getOriginalKey(result.getRow()));
    Assert.assertArrayEquals(value, result.getValue(CF, QUAL));
  }