@Test
  public void prepareConfigurationTest() throws Exception {
    Configuration.defaultInit();
    org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
    mMockHdfsUnderFileSystem.prepareConfiguration("", conf);
    Assert.assertEquals("org.apache.hadoop.hdfs.DistributedFileSystem", conf.get("fs.hdfs.impl"));
    Assert.assertFalse(conf.getBoolean("fs.hdfs.impl.disable.cache", false));
    Assert.assertNotNull(conf.get(Constants.UNDERFS_HDFS_CONFIGURATION));
  }