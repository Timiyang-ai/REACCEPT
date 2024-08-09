protected DataStore getDataStore(ViewInstanceEntity instanceDefinition) {
    Injector originInjector = Guice.createInjector(new DataStoreModule(instanceDefinition));
    return originInjector.getInstance(DataStoreImpl.class);
  }