@Test
  public void mergeHadoopConfigurationTest() {
    Configuration.defaultInit();
    org.apache.hadoop.conf.Configuration hadoopConfig = new org.apache.hadoop.conf.Configuration();
    hadoopConfig.set(Constants.S3N_ACCESS_KEY, TEST_S3_ACCCES_KEY);
    hadoopConfig.set(Constants.S3N_SECRET_KEY, TEST_S3_SECRET_KEY);
    hadoopConfig.set(TEST_ALLUXIO_PROPERTY, TEST_ALLUXIO_VALUE);

    // This hadoop config will not be loaded into Alluxio configuration.
    hadoopConfig.set("hadoop.config.parameter", "hadoop config value");

    Map<String, String> before = Configuration.toMap();
    ConfUtils.mergeHadoopConfiguration(hadoopConfig);
    Map<String, String> after = Configuration.toMap();
    Assert.assertEquals(before.size() + 3, after.size());
    Assert.assertEquals(TEST_S3_ACCCES_KEY, Configuration.get(Constants.S3N_ACCESS_KEY));
    Assert.assertEquals(TEST_S3_SECRET_KEY, Configuration.get(Constants.S3N_SECRET_KEY));
    Assert.assertEquals(TEST_ALLUXIO_VALUE, Configuration.get(TEST_ALLUXIO_PROPERTY));
  }