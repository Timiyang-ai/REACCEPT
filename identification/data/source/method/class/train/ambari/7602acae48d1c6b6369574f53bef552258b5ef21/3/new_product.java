public List<AlertTargetEntity> findAllTargets() {
    TypedQuery<AlertTargetEntity> query = entityManagerProvider.get().createNamedQuery(
        "AlertTargetEntity.findAll", AlertTargetEntity.class);

    return daoUtils.selectList(query);
  }