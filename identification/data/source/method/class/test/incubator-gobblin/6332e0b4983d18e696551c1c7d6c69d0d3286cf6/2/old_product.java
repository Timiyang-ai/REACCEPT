public static Properties loadGenericJobConfig(Properties properties, Path jobConfigPath, Path jobConfigPathDir)
      throws ConfigurationException, IOException {
    List<Properties> commonPropsList = Lists.newArrayList();
    getCommonProperties(commonPropsList, jobConfigPathDir, jobConfigPath.getParent());
    // Add the framework configuration properties to the end
    commonPropsList.add(properties);

    Properties jobProps = new Properties();
    // Include common properties in reverse order
    for (Properties commonProps : Lists.reverse(commonPropsList)) {
      jobProps.putAll(commonProps);
    }

    // Then load the job configuration properties defined in the job configuration file
    jobProps.putAll(ConfigurationConverter.getProperties(
        new PropertiesConfiguration(new Path("file://", jobConfigPath).toUri().toURL())));

    if (jobProps.containsKey(ConfigurationKeys.JOB_TEMPLATE_PATH)) {
      jobProps = (new SimpleGeneralJobTemplate(
          jobProps.getProperty(ConfigurationKeys.JOB_TEMPLATE_PATH))).getResolvedConfigAsProperties(jobProps);
    }

    jobProps.setProperty(ConfigurationKeys.JOB_CONFIG_FILE_PATH_KEY, jobConfigPath.toString());
    return jobProps;
  }