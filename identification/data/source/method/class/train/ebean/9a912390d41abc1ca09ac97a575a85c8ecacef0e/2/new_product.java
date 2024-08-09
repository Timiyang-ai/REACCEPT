public void loadFromProperties(Properties properties) {
    this.properties = properties;
    this.clusterActive = getProperty(properties, "ebean.cluster.active", clusterActive);
  }