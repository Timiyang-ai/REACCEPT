public static InstancedConfiguration mergeHadoopConfiguration(
      org.apache.hadoop.conf.Configuration hadoopConf, AlluxioProperties alluxioProps) {
    // Load Alluxio configuration if any and merge to the one in Alluxio file system
    // Push Alluxio configuration to the Job configuration
    Properties alluxioConfProperties = new Properties();
    // Load any Alluxio configuration parameters existing in the Hadoop configuration.
    for (Map.Entry<String, String> entry : hadoopConf) {
      String propertyName = entry.getKey();
      if (PropertyKey.isValid(propertyName)) {
        alluxioConfProperties.put(propertyName, entry.getValue());
      }
    }
    LOG.info("Loading Alluxio properties from Hadoop configuration: {}", alluxioConfProperties);
    // Merge the relevant Hadoop configuration into Alluxio's configuration.

    alluxioProps.merge(alluxioConfProperties, Source.RUNTIME);
    // Creting a new instanced configuration from an AlluxioProperties object isn't expensive.
    InstancedConfiguration mergedConf = new InstancedConfiguration(alluxioProps);
    mergedConf.validate();
    return mergedConf;
  }