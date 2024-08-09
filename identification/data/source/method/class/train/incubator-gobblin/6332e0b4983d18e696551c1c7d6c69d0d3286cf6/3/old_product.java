public static List<Properties> loadGenericJobConfigs(Properties properties, Path commonPropsPath,
      Path jobConfigPathDir)
      throws ConfigurationException, IOException {
    List<Properties> commonPropsList = Lists.newArrayList();
    // Start from the parent of parent of the changed common properties file to avoid
    // loading the common properties file here since it will be loaded below anyway
    getCommonProperties(commonPropsList, jobConfigPathDir, commonPropsPath.getParent().getParent());
    // Add the framework configuration properties to the end
    commonPropsList.add(properties);

    Properties commonProps = new Properties();
    // Include common properties in reverse order
    for (Properties pros : Lists.reverse(commonPropsList)) {
      commonProps.putAll(pros);
    }

    List<Properties> jobConfigs = Lists.newArrayList();
    // The common properties file will be loaded here
    loadGenericJobConfigsRecursive(jobConfigs, commonProps, getJobConfigurationFileExtensions(properties),
        commonPropsPath.getParent());
    return jobConfigs;
  }