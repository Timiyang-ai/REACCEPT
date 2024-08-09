public static Configuration loadFromHadoopConfiguration(
      org.apache.hadoop.conf.Configuration source) {
    // Load Alluxio configuration if any and merge to the one in Alluxio file system
    // Push Alluxio configuration to the Job configuration
    Properties alluxioConfProperties = new Properties();
    // Load any Alluxio configuration parameters existing in the Hadoop configuration.
    for (Map.Entry<String, String> entry : source) {
      String propertyName = entry.getKey();
      // TODO(gene): use a better way to enumerate every Alluxio configuration parameter
      if (propertyName.startsWith("alluxio.")
          || propertyName.equals(Constants.S3_ACCESS_KEY)
          || propertyName.equals(Constants.S3_SECRET_KEY)
          || propertyName.equals(Constants.SWIFT_API_KEY)
          || propertyName.equals(Constants.SWIFT_AUTH_METHOD_KEY)
          || propertyName.equals(Constants.SWIFT_AUTH_PORT_KEY)
          || propertyName.equals(Constants.SWIFT_AUTH_URL_KEY)
          || propertyName.equals(Constants.SWIFT_PASSWORD_KEY)
          || propertyName.equals(Constants.SWIFT_TENANT_KEY)
          || propertyName.equals(Constants.SWIFT_USE_PUBLIC_URI_KEY)
          || propertyName.equals(Constants.SWIFT_USER_KEY)) {
        alluxioConfProperties.put(propertyName, entry.getValue());
      }
    }
    LOG.info("Loading Alluxio properties from Hadoop configuration: {}", alluxioConfProperties);
    // FIX return Configuration.fromMap(alluxioConfProperties);
    return null;
  }