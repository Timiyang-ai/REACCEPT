protected DataStore getDataStore(ViewInstanceEntity instanceDefinition) {
    if (!dataStoreModules.containsKey(instanceDefinition)) {
      DataStoreModule module = new DataStoreModule(instanceDefinition,"ambari-view-migration");
      dataStoreModules.put(instanceDefinition, module);
    }
    Injector injector = Guice.createInjector(dataStoreModules.get(instanceDefinition));
    return injector.getInstance(DataStoreImpl.class);
  }