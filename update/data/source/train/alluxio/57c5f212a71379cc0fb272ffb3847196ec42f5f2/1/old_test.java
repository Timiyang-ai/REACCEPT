@Test
  public void isFullTest() throws Exception {
    long size = mWriter.byteCount() + KEY1.length + VALUE1.length;
    TachyonConf originalConf = ClientContext.getConf();
    TachyonConf conf = new TachyonConf();
    conf.set(Constants.KEY_VALUE_PARTITION_SIZE_BYTES_MAX, String.valueOf(size));
    ClientContext.reset(conf);
    mWriter = new BaseKeyValuePartitionWriter(mOutStream);
    mWriter.put(KEY1, VALUE1);
    Assert.assertTrue(mWriter.isFull());
    ClientContext.reset(originalConf);
  }