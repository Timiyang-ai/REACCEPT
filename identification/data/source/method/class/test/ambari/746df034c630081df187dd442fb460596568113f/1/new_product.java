@RequiresSession
  public List<HostVersionEntity> findByClusterStackAndVersion(
      String clusterName, StackId stackId, String version) {
    final TypedQuery<HostVersionEntity> query = entityManagerProvider.get().createNamedQuery("hostVersionByClusterAndStackAndVersion", HostVersionEntity.class);
    query.setParameter("clusterName", clusterName);
    query.setParameter("stackName", stackId.getStackName());
    query.setParameter("stackVersion", stackId.getStackVersion());
    query.setParameter("version", version);

    return daoUtils.selectList(query);
  }