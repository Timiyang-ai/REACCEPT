public static Properties loadGenericJobConfig(Properties properties, Path jobConfigPath, Path jobConfigPathDir)
      throws ConfigurationException, IOException {

    PullFileLoader loader =
        new PullFileLoader(jobConfigPathDir, jobConfigPathDir.getFileSystem(new Configuration()),
            getJobConfigurationFileExtensions(properties), PullFileLoader.DEFAULT_HOCON_PULL_FILE_EXTENSIONS);

    Config config = loader.loadPullFile(jobConfigPath, ConfigFactory.parseProperties(properties), true);
    return ConfigUtils.configToProperties(config);
  }