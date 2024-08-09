public static void mergeHadoopConfiguration(org.apache.hadoop.conf.Configuration source,
      AlluxioConfiguration alluxioConfiguration) {
    // Load Alluxio configuration if any and merge to the one in Alluxio file system
    // Push Alluxio configuration to the Job configuration
    Properties alluxioConfProperties = new Properties();
    // Load any Alluxio configuration parameters existing in the Hadoop configuration.
    for (Map.Entry<String, String> entry : source) {
      String propertyName = entry.getKey();
      if (PropertyKey.isValid(propertyName)) {
        alluxioConfProperties.put(propertyName, entry.getValue());
      }
    }
    LOG.info("Loading Alluxio properties from Hadoop configuration: {}", alluxioConfProperties);
    // Merge the relevant Hadoop configuration into Alluxio's configuration.
    // TODO(jiri): support multiple client configurations (ALLUXIO-2034)
    alluxioConfiguration.merge(alluxioConfProperties, Source.RUNTIME);
    alluxioConfiguration.validate();
  }