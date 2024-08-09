public List<AlertDefinitionEntity> findAll(long clusterId) {
    TypedQuery<AlertDefinitionEntity> query = entityManagerProvider.get().createNamedQuery(
        "AlertDefinitionEntity.findAllInCluster", AlertDefinitionEntity.class);

    query.setParameter("clusterId", clusterId);

    return daoUtils.selectList(query);
  }