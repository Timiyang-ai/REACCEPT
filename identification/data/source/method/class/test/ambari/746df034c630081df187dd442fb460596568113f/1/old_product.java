@RequiresSession
  public List<HostVersionEntity> findByClusterStackAndVersion(String clusterName, String stack, String version) {
    final TypedQuery<HostVersionEntity> query = entityManagerProvider.get().createNamedQuery("hostVersionByClusterAndStackAndVersion", HostVersionEntity.class);
    query.setParameter("clusterName", clusterName);
    query.setParameter("stack", stack);
    query.setParameter("version", version);

    return daoUtils.selectList(query);
  }