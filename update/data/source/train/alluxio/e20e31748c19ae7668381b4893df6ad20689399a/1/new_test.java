@Test
  public void canPutKeyValueTest() throws Exception {
    long size = mWriter.byteCount() + KEY1.length + VALUE1.length + 2 * Constants.BYTES_IN_INTEGER;
    TachyonConf originalConf = ClientContext.getConf();
    TachyonConf conf = new TachyonConf();
    conf.set(Constants.KEY_VALUE_PARTITION_SIZE_BYTES_MAX, String.valueOf(size));
    ClientContext.reset(conf);
    mWriter = new BaseKeyValuePartitionWriter(mOutStream);
    Assert.assertTrue(mWriter.canPut(KEY1, VALUE1));
    mWriter.put(KEY1, VALUE1);
    Assert.assertFalse(mWriter.canPut(KEY1, VALUE1));
    ClientContext.reset(originalConf);
  }