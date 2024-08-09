public PersistentResource getRelation(String relation, String id) {
        /* getRelation performs read permission checks */
        Set<PersistentResource> resources = getRelation(relation);
        for (PersistentResource childResource : resources) {
            if (childResource.matchesId(id)) {
                return childResource;
            }
        }
        throw new InvalidObjectIdentifierException(id);
    }