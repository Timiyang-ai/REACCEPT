public static List<Properties> loadGenericJobConfigs(Properties properties, Path commonPropsPath,
      Path jobConfigPathDir)
      throws ConfigurationException, IOException {

    PullFileLoader loader =
        new PullFileLoader(jobConfigPathDir, jobConfigPathDir.getFileSystem(new Configuration()),
            getJobConfigurationFileExtensions(properties), PullFileLoader.DEFAULT_HOCON_PULL_FILE_EXTENSIONS);
    Collection<Config> configs =
        loader.loadPullFilesRecursively(commonPropsPath.getParent(), ConfigFactory.parseProperties(properties), true);

    List<Properties> jobConfigs = Lists.newArrayList();
    for (Config config : configs) {
      jobConfigs.add(ConfigUtils.configToProperties(config));
    }

    return jobConfigs;
  }