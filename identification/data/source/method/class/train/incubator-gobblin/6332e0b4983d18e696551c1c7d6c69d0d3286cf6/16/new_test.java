  @Test
  public void testloadGenericJobConfig()
      throws ConfigurationException, IOException {
    Path jobConfigPath = new Path(this.subDir11.getAbsolutePath(), "test111.pull");
    Properties properties = new Properties();
    properties.setProperty(ConfigurationKeys.JOB_CONFIG_FILE_GENERAL_PATH_KEY, this.jobConfigDir.getAbsolutePath());
    Properties jobProps =
        SchedulerUtils.loadGenericJobConfig(properties, jobConfigPath, new Path(this.jobConfigDir.getAbsolutePath()),
            JobSpecResolver.builder(ConfigFactory.empty()).build());

    Assert.assertEquals(jobProps.stringPropertyNames().size(), 7);
    Assert.assertTrue(jobProps.containsKey(ConfigurationKeys.JOB_CONFIG_FILE_DIR_KEY) || jobProps.containsKey(
        ConfigurationKeys.JOB_CONFIG_FILE_GENERAL_PATH_KEY));
    Assert.assertTrue(jobProps.containsKey(ConfigurationKeys.JOB_CONFIG_FILE_PATH_KEY));
    Assert.assertEquals(jobProps.getProperty("k1"), "d1");
    Assert.assertEquals(jobProps.getProperty("k2"), "a2");
    Assert.assertEquals(jobProps.getProperty("k3"), "a3");
    Assert.assertEquals(jobProps.getProperty("k8"), "a8");
    Assert.assertEquals(jobProps.getProperty("k9"), "a8");
  }