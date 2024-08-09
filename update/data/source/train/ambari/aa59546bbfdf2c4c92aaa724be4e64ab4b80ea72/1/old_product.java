@RequiresSession
  public UpgradeEntity findLastUpgradeForCluster(long clusterId) {
    TypedQuery<UpgradeEntity> query = entityManagerProvider.get().createNamedQuery(
        "UpgradeEntity.findLatestForCluster", UpgradeEntity.class);
    query.setMaxResults(1);
    query.setParameter("clusterId", clusterId);
    query.setParameter("direction", Direction.UPGRADE);

    query.setHint(QueryHints.REFRESH, HintValues.TRUE);

    return daoUtils.selectSingle(query);
  }