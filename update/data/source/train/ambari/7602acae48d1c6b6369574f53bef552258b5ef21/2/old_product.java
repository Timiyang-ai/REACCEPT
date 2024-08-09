@RequiresSession
  public AlertGroupEntity findGroupByName(long clusterId, String groupName) {
    TypedQuery<AlertGroupEntity> query = entityManagerProvider.get().createNamedQuery(
        "AlertGroupEntity.findByNameInCluster", AlertGroupEntity.class);

    query.setParameter("clusterId", clusterId);
    query.setParameter("groupName", groupName);

    return daoUtils.selectSingle(query);
  }