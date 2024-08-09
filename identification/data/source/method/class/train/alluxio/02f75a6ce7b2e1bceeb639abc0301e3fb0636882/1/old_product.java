public void merge(Map<?, ?> properties, Source source) {
    if (properties == null || properties.isEmpty()) {
      return;
    }
    // merge the properties
    for (Map.Entry<?, ?> entry : properties.entrySet()) {
      String key = entry.getKey().toString().trim();
      String value = entry.getValue() == null ? null : entry.getValue().toString().trim();
      PropertyKey propertyKey;
      if (PropertyKey.isValid(key)) {
        propertyKey = PropertyKey.fromString(key);
      } else {
        // Add unrecognized properties
        LOG.debug("Property {} from source {} is unrecognized", key, source);
        // Workaround for issue https://alluxio.atlassian.net/browse/ALLUXIO-3108
        // This will register the key as a valid PropertyKey
        // TODO(adit): Do not add properties unrecognized by Ufs extensions when Configuration
        // is made dynamic
        propertyKey = new PropertyKey.Builder(key).setIsBuiltIn(false).build();
      }
      put(propertyKey, value, source);
    }
    mHash.markOutdated();
  }