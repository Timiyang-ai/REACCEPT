private void SetViewInstanceProperties(ViewInstanceEntity instanceEntity, ViewConfig viewConfig, ClassLoader classLoader) throws SystemException {

    Map<String, String> properties = new HashMap<String, String>();

    HashSet<ViewInstancePropertyEntity> propertyEntities =
        new HashSet<ViewInstancePropertyEntity>(instanceEntity.getProperties());

    for (ViewInstancePropertyEntity viewInstancePropertyEntity : propertyEntities) {
      properties.put(viewInstancePropertyEntity.getName(), viewInstancePropertyEntity.getValue());
    }
    SetViewInstanceProperties( instanceEntity,  properties, viewConfig,  classLoader);
  }