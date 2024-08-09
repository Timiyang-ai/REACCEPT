  @Test
  public void testloadGenericJobConfigs()
      throws ConfigurationException, IOException {
    Properties properties = new Properties();
    properties.setProperty(ConfigurationKeys.JOB_CONFIG_FILE_GENERAL_PATH_KEY, this.jobConfigDir.getAbsolutePath());
    List<Properties> jobConfigs = SchedulerUtils.loadGenericJobConfigs(properties, JobSpecResolver.mock());
    Assert.assertEquals(jobConfigs.size(), 4);

    // test-job-conf-dir/test1/test11/test111.pull
    Properties jobProps1 = getJobConfigForFile(jobConfigs, "test111.pull");
    Assert.assertEquals(jobProps1.stringPropertyNames().size(), 7);
    Assert.assertTrue(jobProps1.containsKey(ConfigurationKeys.JOB_CONFIG_FILE_DIR_KEY) || jobProps1.containsKey(
        ConfigurationKeys.JOB_CONFIG_FILE_GENERAL_PATH_KEY));
    Assert.assertTrue(jobProps1.containsKey(ConfigurationKeys.JOB_CONFIG_FILE_PATH_KEY));
    Assert.assertEquals(jobProps1.getProperty("k1"), "d1");
    Assert.assertEquals(jobProps1.getProperty("k2"), "a2");
    Assert.assertEquals(jobProps1.getProperty("k3"), "a3");
    Assert.assertEquals(jobProps1.getProperty("k8"), "a8");
    Assert.assertEquals(jobProps1.getProperty("k9"), "a8");

    // test-job-conf-dir/test1/test11.pull
    Properties jobProps2 = getJobConfigForFile(jobConfigs, "test11.pull");
    Assert.assertEquals(jobProps2.stringPropertyNames().size(), 6);
    Assert.assertTrue(jobProps2.containsKey(ConfigurationKeys.JOB_CONFIG_FILE_DIR_KEY) || jobProps1.containsKey(
        ConfigurationKeys.JOB_CONFIG_FILE_GENERAL_PATH_KEY));
    Assert.assertTrue(jobProps2.containsKey(ConfigurationKeys.JOB_CONFIG_FILE_PATH_KEY));
    Assert.assertEquals(jobProps2.getProperty("k1"), "c1");
    Assert.assertEquals(jobProps2.getProperty("k2"), "a2");
    Assert.assertEquals(jobProps2.getProperty("k3"), "b3");
    Assert.assertEquals(jobProps2.getProperty("k6"), "a6");

    // test-job-conf-dir/test1/test12.PULL
    Properties jobProps3 = getJobConfigForFile(jobConfigs, "test12.PULL");
    Assert.assertEquals(jobProps3.stringPropertyNames().size(), 6);
    Assert.assertTrue(jobProps3.containsKey(ConfigurationKeys.JOB_CONFIG_FILE_DIR_KEY) || jobProps1.containsKey(
        ConfigurationKeys.JOB_CONFIG_FILE_GENERAL_PATH_KEY));
    Assert.assertTrue(jobProps3.containsKey(ConfigurationKeys.JOB_CONFIG_FILE_PATH_KEY));
    Assert.assertEquals(jobProps3.getProperty("k1"), "b1");
    Assert.assertEquals(jobProps3.getProperty("k2"), "a2");
    Assert.assertEquals(jobProps3.getProperty("k3"), "a3");
    Assert.assertEquals(jobProps3.getProperty("k7"), "a7");

    // test-job-conf-dir/test2/test21.PULL
    Properties jobProps4 = getJobConfigForFile(jobConfigs, "test21.PULL");
    Assert.assertEquals(jobProps4.stringPropertyNames().size(), 5);
    Assert.assertTrue(jobProps4.containsKey(ConfigurationKeys.JOB_CONFIG_FILE_DIR_KEY) || jobProps1.containsKey(
        ConfigurationKeys.JOB_CONFIG_FILE_GENERAL_PATH_KEY));
    Assert.assertTrue(jobProps4.containsKey(ConfigurationKeys.JOB_CONFIG_FILE_PATH_KEY));
    Assert.assertEquals(jobProps4.getProperty("k1"), "a1");
    Assert.assertEquals(jobProps4.getProperty("k2"), "b2");
    Assert.assertEquals(jobProps4.getProperty("k5"), "b5");
  }