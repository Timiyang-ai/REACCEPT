public PersistentResource getRelation(String relation, String id) {

        Predicate idFilter = new Predicate(relation, Operator.IN, Collections.singletonList(id));
        Set<Predicate> filters = Collections.singleton(idFilter);

        /* getRelation performs read permission checks */
        Set<PersistentResource> resources = getRelation(relation, filters);
        for (PersistentResource childResource : resources) {
            if (childResource.matchesId(id)) {
                return childResource;
            }
        }
        throw new InvalidObjectIdentifierException(id);
    }