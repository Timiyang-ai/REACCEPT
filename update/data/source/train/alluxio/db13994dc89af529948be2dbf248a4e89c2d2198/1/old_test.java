@Test
  public void loadFromHadoopConfigurationTest() {
    org.apache.hadoop.conf.Configuration hadoopConfig = new org.apache.hadoop.conf.Configuration();
    hadoopConfig.set(Constants.S3_ACCESS_KEY, TEST_S3_ACCCES_KEY);
    hadoopConfig.set(Constants.S3_SECRET_KEY, TEST_S3_SECRET_KEY);
    hadoopConfig.set(Constants.WORKER_MEMORY_SIZE, TEST_WORKER_MEMORY_SIZE);
    // This hadoop config will not be loaded into Alluxio configuration.
    hadoopConfig.set("hadoop.config.parameter", "hadoop config value");

    Configuration configuration = ConfUtils.loadFromHadoopConfiguration(hadoopConfig);
    Assert.assertEquals(3, configuration.toMap().size());
    Assert.assertEquals(TEST_S3_ACCCES_KEY, configuration.get(Constants.S3_ACCESS_KEY));
    Assert.assertEquals(TEST_S3_SECRET_KEY, configuration.get(Constants.S3_SECRET_KEY));
    Assert.assertEquals(TEST_WORKER_MEMORY_SIZE, configuration.get(Constants.WORKER_MEMORY_SIZE));
  }