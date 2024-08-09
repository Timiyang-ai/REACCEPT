public static Properties loadGenericJobConfig(Properties sysProps, Path jobConfigPath, Path jobConfigPathDir)
      throws ConfigurationException, IOException {

    PullFileLoader loader = new PullFileLoader(jobConfigPathDir, jobConfigPathDir.getFileSystem(new Configuration()),
        getJobConfigurationFileExtensions(sysProps), PullFileLoader.DEFAULT_HOCON_PULL_FILE_EXTENSIONS);

    Config sysConfig = ConfigUtils.propertiesToConfig(sysProps);
    Config config = loader.loadPullFile(jobConfigPath, sysConfig, true);
    return resolveTemplate(ConfigUtils.configToProperties(config));
  }