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
    HadoopConfigurationUtils.mergeHadoopConfiguration(hadoopConfig, Configuration.global());
    Assert.assertEquals(TEST_S3_ACCCES_KEY, Configuration.get(PropertyKey.S3A_ACCESS_KEY));
    Assert.assertEquals(TEST_S3_SECRET_KEY, Configuration.get(PropertyKey.S3A_SECRET_KEY));
    Assert.assertEquals(Source.RUNTIME, Configuration.getSource(PropertyKey.S3A_ACCESS_KEY));
    Assert.assertEquals(Source.RUNTIME, Configuration.getSource(PropertyKey.S3A_SECRET_KEY));
    Assert.assertTrue(Configuration.getBoolean(PropertyKey.ZOOKEEPER_ENABLED));
    Assert.assertEquals("host1:port1,host2:port2;host3:port3",
        Configuration.get(PropertyKey.ZOOKEEPER_ADDRESS));
  }