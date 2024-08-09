public static <E extends Enum<E>> WithExplicitOrdering<E>
      newInstanceWithExplicitOrdering(Class<E> enumClass, Policy policy) {
    // createNodes maps each enumClass to a Map with the corresponding enum key
    // type.
    checkNotNull(enumClass);
    checkNotNull(policy);
    @SuppressWarnings("unchecked")
    Map<E, LockGraphNode> lockGraphNodes =
        (Map<E, LockGraphNode>) getOrCreateNodes(enumClass);
    return new WithExplicitOrdering<E>(policy, lockGraphNodes);
  }