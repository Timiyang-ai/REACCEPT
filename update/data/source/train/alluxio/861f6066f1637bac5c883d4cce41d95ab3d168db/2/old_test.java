@Test
  public void loadFromHadoopConfigurationTest() {
    Configuration hadoopConfig = new Configuration();
    hadoopConfig.set(Constants.S3_ACCESS_KEY, TEST_S3_ACCCES_KEY);
    hadoopConfig.set(Constants.S3_SECRET_KEY, TEST_S3_SECRET_KEY);
    hadoopConfig.set(Constants.WORKER_MEMORY_SIZE, TEST_WORKER_MEMORY_SIZE);
    // This hadoop config will not be loaded into TachyonConf.
    hadoopConfig.set("hadoop.config.parameter", "hadoop config value");

    TachyonConf tachyonConf = ConfUtils.loadFromHadoopConfiguration(hadoopConfig);
    Assert.assertEquals(3, tachyonConf.toMap().size());
    Assert.assertEquals(TEST_S3_ACCCES_KEY, tachyonConf.get(Constants.S3_ACCESS_KEY));
    Assert.assertEquals(TEST_S3_SECRET_KEY, tachyonConf.get(Constants.S3_SECRET_KEY));
    Assert.assertEquals(TEST_WORKER_MEMORY_SIZE, tachyonConf.get(Constants.WORKER_MEMORY_SIZE));
  }