public void loadSettings(PropertiesWrapper properties) {

    active = properties.getBoolean("docstore.active", active);
    url = properties.get("docstore.url", url);
    persist = properties.getEnum(DocStoreEvent.class, "docstore.persist", persist);
    bulkBatchSize = properties.getInt("docstore.bulkBatchSize", bulkBatchSize);
    dropCreate = properties.getBoolean("docstore.dropCreate", dropCreate);
    mappingPath = properties.get("docstore.mappingPath", mappingPath);
    mappingSuffix = properties.get("docstore.mappingSuffix", mappingSuffix);
    pathToResources = properties.get("docstore.pathToResources", pathToResources);
  }