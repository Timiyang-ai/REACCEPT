public AlertDefinitionEntity findByName(long clusterId, String definitionName) {
    TypedQuery<AlertDefinitionEntity> query = entityManagerProvider.get().createNamedQuery(
        "AlertDefinitionEntity.findByName", AlertDefinitionEntity.class);

    query.setParameter("clusterId", clusterId);
    query.setParameter("definitionName", definitionName);

    return daoUtils.selectSingle(query);
  }