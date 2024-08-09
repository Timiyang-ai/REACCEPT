@RequiresSession
  public HostVersionEntity findByClusterStackVersionAndHost(String clusterName, String stack, String version, String hostName) {
    final TypedQuery<HostVersionEntity> query = entityManagerProvider.get()
        .createNamedQuery("hostVersionByClusterStackVersionAndHostname", HostVersionEntity.class);
    query.setParameter("clusterName", clusterName);
    query.setParameter("stack", stack);
    query.setParameter("version", version);
    query.setParameter("hostName", hostName);

    return daoUtils.selectSingle(query);
  }