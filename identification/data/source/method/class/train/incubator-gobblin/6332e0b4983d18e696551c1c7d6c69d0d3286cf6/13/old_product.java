public static List<Properties> loadGenericJobConfigs(Properties properties)
      throws ConfigurationException, IOException {
    List<Properties> jobConfigs = Lists.newArrayList();
    loadGenericJobConfigsRecursive(jobConfigs, properties, getJobConfigurationFileExtensions(properties),
        new Path(properties.getProperty(ConfigurationKeys.JOB_CONFIG_FILE_DIR_KEY)));
    return jobConfigs;
  }