public List<AlertGroupEntity> findAllGroups() {
    TypedQuery<AlertGroupEntity> query = entityManagerProvider.get().createNamedQuery(
        "AlertGroupEntity.findAll", AlertGroupEntity.class);

    return daoUtils.selectList(query);
  }