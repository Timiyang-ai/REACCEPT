public static Configuration loadFromHadoopConfiguration(org.apache.hadoop.conf.Configuration source) {
    // Load TachyonConf if any and merge to the one in TachyonFS
    // Push TachyonConf to the Job conf
    Properties tachyonConfProperties = null;
    if (source.get(Constants.TACHYON_CONF_SITE) != null) {
      LOG.info("Found TachyonConf site from Job configuration for Tachyon");
      try {
        tachyonConfProperties = DefaultStringifier.load(source, Constants.TACHYON_CONF_SITE,
            Properties.class);
      } catch (IOException e) {
        LOG.error("Unable to load TachyonConf from Hadoop configuration", e);
        throw new RuntimeException(e);
      }
    }
    if (tachyonConfProperties == null) {
      tachyonConfProperties = new Properties();
    }
    // Load any Tachyon configuration parameters existing in the Hadoop configuration.
    for (Map.Entry<String, String> entry : source) {
      String propertyName = entry.getKey();
      // TODO(gene): use a better way to enumerate every Tachyon configuration parameter
      if (propertyName.startsWith("alluxio.")
          || propertyName.equals(Constants.S3_ACCESS_KEY)
          || propertyName.equals(Constants.S3_SECRET_KEY)) {
        tachyonConfProperties.put(propertyName, entry.getValue());
      }
    }
    LOG.info("Loading Tachyon properties from Hadoop configuration: {}", tachyonConfProperties);
    return new Configuration(tachyonConfProperties);
  }