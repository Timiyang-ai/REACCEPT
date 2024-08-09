  @Test
  public void prepareConfiguration() throws Exception {
    UnderFileSystemConfiguration ufsConf =
        UnderFileSystemConfiguration.defaults(ConfigurationTestUtils.defaults());
    org.apache.hadoop.conf.Configuration conf = HdfsUnderFileSystem.createConfiguration(ufsConf);
    Assert.assertEquals(ufsConf.get(PropertyKey.UNDERFS_HDFS_IMPL), conf.get("fs.hdfs.impl"));
    Assert.assertTrue(conf.getBoolean("fs.hdfs.impl.disable.cache", false));
  }