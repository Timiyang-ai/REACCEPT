@RequiresSession
  public HostVersionEntity findByClusterStackVersionAndHost(String clusterName,
      StackId stackId, String version, String hostName) {

    final TypedQuery<HostVersionEntity> query = entityManagerProvider.get()
        .createNamedQuery("hostVersionByClusterStackVersionAndHostname", HostVersionEntity.class);
    query.setParameter("clusterName", clusterName);
    query.setParameter("stackName", stackId.getStackName());
    query.setParameter("stackVersion", stackId.getStackVersion());
    query.setParameter("version", version);
    query.setParameter("hostName", hostName);

    return daoUtils.selectSingle(query);
  }