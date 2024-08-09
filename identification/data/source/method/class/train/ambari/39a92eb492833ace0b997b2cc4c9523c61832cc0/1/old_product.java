@RequiresSession
  public AlertDefinitionEntity findByName(String definitionName) {
    TypedQuery<AlertDefinitionEntity> query = entityManagerProvider.get().createNamedQuery(
        "AlertDefinitionEntity.findByName", AlertDefinitionEntity.class);

    query.setParameter("definitionName", definitionName);

    return daoUtils.selectSingle(query);
  }