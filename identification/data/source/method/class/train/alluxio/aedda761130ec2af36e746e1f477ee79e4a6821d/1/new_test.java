@Test
  public void mergeHadoopConfigurationTest() {
    Configuration.defaultInit();
    org.apache.hadoop.conf.Configuration hadoopConfig = new org.apache.hadoop.conf.Configuration();
    hadoopConfig.set(PropertyKey.S3N_ACCESS_KEY.toString(), TEST_S3_ACCCES_KEY);
    hadoopConfig.set(PropertyKey.S3N_SECRET_KEY.toString(), TEST_S3_SECRET_KEY);

    // This hadoop config will not be loaded into Alluxio configuration.
    hadoopConfig.set("hadoop.config.parameter", "hadoop config value");

    Map<String, String> before = Configuration.toMap();
    ConfUtils.mergeHadoopConfiguration(hadoopConfig);
    Map<String, String> after = Configuration.toMap();
    Assert.assertEquals(before.size() + 3, after.size());
    Assert.assertEquals(TEST_S3_ACCCES_KEY, Configuration.get(PropertyKey.S3N_ACCESS_KEY));
    Assert.assertEquals(TEST_S3_SECRET_KEY, Configuration.get(PropertyKey.S3N_SECRET_KEY));
  }