public AlertTargetEntity findTargetByName(String targetName) {
    TypedQuery<AlertTargetEntity> query = entityManagerProvider.get().createNamedQuery(
        "AlertTargetEntity.findByName", AlertTargetEntity.class);

    query.setParameter("targetName", targetName);

    return daoUtils.selectSingle(query);
  }