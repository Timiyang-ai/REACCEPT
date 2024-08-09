@RequiresSession
  public List<HostVersionEntity> findByClusterHostAndState(String  clusterName, String hostName, RepositoryVersionState state) {
    final TypedQuery<HostVersionEntity> query = entityManagerProvider.get()
        .createNamedQuery("hostVersionByClusterHostnameAndState", HostVersionEntity.class);
    query.setParameter("clusterName", clusterName);
    query.setParameter("hostName", hostName);
    query.setParameter("state", state);

    return daoUtils.selectList(query);
  }