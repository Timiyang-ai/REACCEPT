public PersistentResource getRelation(String relation, String id) {
        Set<PersistentResource> resources = getRelation(relation, Collections.singletonList(id),
                Optional.empty(), Optional.empty(), Optional.empty());
      if (resources.isEmpty()) {
            return null;
      }
      return resources.iterator().next();
    }