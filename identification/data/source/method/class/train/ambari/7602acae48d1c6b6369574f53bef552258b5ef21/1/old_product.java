@RequiresSession
  public AlertDefinitionEntity findById(long definitionId) {
    return entityManagerProvider.get().find(AlertDefinitionEntity.class,
        definitionId);
  }