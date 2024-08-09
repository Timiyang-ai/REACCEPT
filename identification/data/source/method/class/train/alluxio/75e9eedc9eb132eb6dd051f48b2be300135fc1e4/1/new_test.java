@Test
  public void prepareConfiguration() throws Exception {
    org.apache.hadoop.conf.Configuration conf = HdfsUnderFileSystem.createConfiguration(null);
    Assert.assertEquals("org.apache.hadoop.hdfs.DistributedFileSystem", conf.get("fs.hdfs.impl"));
    Assert.assertTrue(conf.getBoolean("fs.hdfs.impl.disable.cache", false));
    Assert.assertNotNull(conf.get(PropertyKey.UNDERFS_HDFS_CONFIGURATION.toString()));
  }