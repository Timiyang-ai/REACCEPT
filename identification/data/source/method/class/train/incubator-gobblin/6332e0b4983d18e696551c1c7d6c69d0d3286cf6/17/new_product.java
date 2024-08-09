public static List<Properties> loadGenericJobConfigs(Properties sysProps, Path commonPropsPath,
      Path jobConfigPathDir, JobSpecResolver resolver)
      throws ConfigurationException, IOException {

    PullFileLoader loader = new PullFileLoader(jobConfigPathDir, jobConfigPathDir.getFileSystem(new Configuration()),
        getJobConfigurationFileExtensions(sysProps), PullFileLoader.DEFAULT_HOCON_PULL_FILE_EXTENSIONS);
    Config sysConfig = ConfigUtils.propertiesToConfig(sysProps);
    Collection<Config> configs =
        loader.loadPullFilesRecursively(commonPropsPath.getParent(), sysConfig, true);

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