public static List<Properties> loadGenericJobConfigs(Properties properties)
      throws ConfigurationException, IOException {
    Path rootPath = new Path(properties.getProperty(ConfigurationKeys.JOB_CONFIG_FILE_GENERAL_PATH_KEY));
    PullFileLoader loader =
        new PullFileLoader(rootPath, rootPath.getFileSystem(new Configuration()), getJobConfigurationFileExtensions(properties),
            PullFileLoader.DEFAULT_HOCON_PULL_FILE_EXTENSIONS);
    Collection<Config>
        configs = loader.loadPullFilesRecursively(rootPath, ConfigFactory.parseProperties(properties), true);

    List<Properties> jobConfigs = Lists.newArrayList();
    for (Config config : configs) {
      jobConfigs.add(ConfigUtils.configToProperties(config));
    }

    return jobConfigs;
  }