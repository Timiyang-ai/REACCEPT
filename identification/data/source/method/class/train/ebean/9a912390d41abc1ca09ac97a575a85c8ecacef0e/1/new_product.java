public void loadFromProperties(Properties properties) {
    this.properties = properties;
    this.active = getProperty(properties, "ebean.cluster.active", active);
    this.serviceName = properties.getProperty("ebean.cluster.serviceName", serviceName);
    this.namespace = properties.getProperty("ebean.cluster.namespace", namespace);
    this.podName = properties.getProperty("ebean.cluster.podName", podName);
    String portParam = properties.getProperty("ebean.cluster.port");
    if (portParam != null) {
      this.port = Integer.parseInt(portParam);
    }
  }