@Test
  public void mergeHadoopConfiguration() {
    org.apache.hadoop.conf.Configuration hadoopConfig = new org.apache.hadoop.conf.Configuration();
    hadoopConfig.set(PropertyKey.S3A_ACCESS_KEY.toString(), TEST_S3_ACCCES_KEY);
    hadoopConfig.set(PropertyKey.S3A_SECRET_KEY.toString(), TEST_S3_SECRET_KEY);
    hadoopConfig.set(TEST_ALLUXIO_PROPERTY, TEST_ALLUXIO_VALUE);
    hadoopConfig.setBoolean(PropertyKey.ZOOKEEPER_ENABLED.getName(), true);
    hadoopConfig.set(PropertyKey.ZOOKEEPER_ADDRESS.getName(),
        "host1:port1,host2:port2;host3:port3");

    // This hadoop config will not be loaded into Alluxio configuration.
    hadoopConfig.set("hadoop.config.parameter", "hadoop config value");
    mConf = HadoopConfigurationUtils.mergeHadoopConfiguration(hadoopConfig, mConf.copyProperties());
    Assert.assertEquals(TEST_S3_ACCCES_KEY, mConf.get(PropertyKey.S3A_ACCESS_KEY));
    Assert.assertEquals(TEST_S3_SECRET_KEY, mConf.get(PropertyKey.S3A_SECRET_KEY));
    Assert.assertEquals(Source.RUNTIME, mConf.getSource(PropertyKey.S3A_ACCESS_KEY));
    Assert.assertEquals(Source.RUNTIME, mConf.getSource(PropertyKey.S3A_SECRET_KEY));
    Assert.assertTrue(mConf.getBoolean(PropertyKey.ZOOKEEPER_ENABLED));
    Assert.assertEquals("host1:port1,host2:port2;host3:port3",
        mConf.get(PropertyKey.ZOOKEEPER_ADDRESS));
  }