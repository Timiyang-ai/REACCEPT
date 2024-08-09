@Test
  public void mergeHadoopConfiguration() {
    org.apache.hadoop.conf.Configuration hadoopConfig = new org.apache.hadoop.conf.Configuration();
    hadoopConfig.set(PropertyKey.S3A_ACCESS_KEY.toString(), TEST_S3_ACCCES_KEY);
    hadoopConfig.set(PropertyKey.S3A_SECRET_KEY.toString(), TEST_S3_SECRET_KEY);
    hadoopConfig.set(TEST_ALLUXIO_PROPERTY, TEST_ALLUXIO_VALUE);

    // This hadoop config will not be loaded into Alluxio configuration.
    hadoopConfig.set("hadoop.config.parameter", "hadoop config value");
    HadoopConfigurationUtils.mergeHadoopConfiguration(hadoopConfig);
    Assert.assertEquals(TEST_S3_ACCCES_KEY, Configuration.get(PropertyKey.S3A_ACCESS_KEY));
    Assert.assertEquals(TEST_S3_SECRET_KEY, Configuration.get(PropertyKey.S3A_SECRET_KEY));
    Assert.assertEquals(Source.RUNTIME, Configuration.getSource(PropertyKey.S3A_ACCESS_KEY));
    Assert.assertEquals(Source.RUNTIME, Configuration.getSource(PropertyKey.S3A_SECRET_KEY));
  }