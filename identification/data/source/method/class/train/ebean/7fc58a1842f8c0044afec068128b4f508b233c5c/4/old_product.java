public void loadSettings(PropertiesWrapper properties) {

    active = properties.getBoolean("docstore.active", active);
    url = properties.get("docstore.url", url);
    persist = properties.getEnum(DocStoreMode.class, "docstore.persist", persist);
    bulkBatchSize = properties.getInt("docstore.bulkBatchSize", bulkBatchSize);
    generateMapping = properties.getBoolean("docstore.generateMapping", generateMapping);
    dropCreate = properties.getBoolean("docstore.dropCreate", dropCreate);
    create = properties.getBoolean("docstore.create", create);
    mappingPath = properties.get("docstore.mappingPath", mappingPath);
    mappingSuffix = properties.get("docstore.mappingSuffix", mappingSuffix);
    pathToResources = properties.get("docstore.pathToResources", pathToResources);
  }