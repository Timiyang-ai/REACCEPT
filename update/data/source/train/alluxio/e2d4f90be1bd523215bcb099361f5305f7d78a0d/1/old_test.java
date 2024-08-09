@Test
  public void prepareConfigurationTest() throws Exception {
    Configuration tConf = new Configuration();
    org.apache.hadoop.conf.Configuration hConf = new org.apache.hadoop.conf.Configuration();
    mMockHdfsUnderFileSystem.prepareConfiguration("", tConf, hConf);
    Assert.assertEquals("org.apache.hadoop.hdfs.DistributedFileSystem", hConf.get("fs.hdfs.impl"));
    Assert.assertFalse(hConf.getBoolean("fs.hdfs.impl.disable.cache", false));
    Assert.assertNotNull(hConf.get(Constants.UNDERFS_HDFS_CONFIGURATION));
  }