  private <E extends Enum<E>>
      CycleDetectingLockFactory.WithExplicitOrdering<E> newInstanceWithExplicitOrdering(
          Class<E> enumClass, Policy policy) {
    return new CycleDetectingLockFactory.WithExplicitOrdering<E>(
        policy, CycleDetectingLockFactory.createNodes(enumClass));
  }