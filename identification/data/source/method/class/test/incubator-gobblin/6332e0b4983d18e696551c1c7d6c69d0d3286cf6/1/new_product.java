public static List<Properties> loadGenericJobConfigs(Properties sysProps, JobSpecResolver resolver)
      throws ConfigurationException, IOException {
    Path rootPath = new Path(sysProps.getProperty(ConfigurationKeys.JOB_CONFIG_FILE_GENERAL_PATH_KEY));
    PullFileLoader loader = new PullFileLoader(rootPath, rootPath.getFileSystem(new Configuration()),
        getJobConfigurationFileExtensions(sysProps), PullFileLoader.DEFAULT_HOCON_PULL_FILE_EXTENSIONS);
    Config sysConfig = ConfigUtils.propertiesToConfig(sysProps);
    Collection<Config> configs =
        loader.loadPullFilesRecursively(rootPath, sysConfig, true);

    List<Properties> jobConfigs = Lists.newArrayList();
    for (Config config : configs) {
      try {
        jobConfigs.add(resolveTemplate(ConfigUtils.configToProperties(config), resolver));
      } catch (IOException ioe) {
        LOGGER.error("Could not parse job config at " + ConfigUtils.getString(config,
            ConfigurationKeys.JOB_CONFIG_FILE_PATH_KEY, "Unknown path"), ioe);
      }
    }

    return jobConfigs;
  }