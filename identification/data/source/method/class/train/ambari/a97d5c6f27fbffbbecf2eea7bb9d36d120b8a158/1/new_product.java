private void setViewInstanceProperties(ViewInstanceEntity instanceEntity, InstanceConfig instanceConfig, ViewConfig viewConfig, ClassLoader classLoader) throws SystemException {

    Map<String, String> properties = new HashMap<String, String>();

    for (PropertyConfig propertyConfig : instanceConfig.getProperties()) {
      properties.put(propertyConfig.getKey(), propertyConfig.getValue());
    }
    setViewInstanceProperties(instanceEntity, properties, viewConfig, classLoader);
  }