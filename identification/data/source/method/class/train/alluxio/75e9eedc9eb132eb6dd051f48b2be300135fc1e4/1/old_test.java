@Test
  public void prepareConfiguration() throws Exception {
    org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
    mHdfsUnderFileSystem.prepareConfiguration("", conf);
    Assert.assertEquals("org.apache.hadoop.hdfs.DistributedFileSystem", conf.get("fs.hdfs.impl"));
    Assert.assertTrue(conf.getBoolean("fs.hdfs.impl.disable.cache", false));
    Assert.assertNotNull(conf.get(PropertyKey.UNDERFS_HDFS_CONFIGURATION.toString()));
  }