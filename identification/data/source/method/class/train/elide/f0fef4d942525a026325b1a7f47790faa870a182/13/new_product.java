public PersistentResource getRelation(String relation, String id) {
        Set<Predicate> filters;
        // TODO: UUID's are troublesome here from Patch Extension. We should consider an alternative approach for this.
        if (requestScope instanceof PatchRequestScope) {
            filters = Collections.emptySet();
        } else {
            Object idVal = CoerceUtil.coerce(id,
                    dictionary.getIdType(dictionary.getParameterizedType(getResourceClass(), relation)));
            Predicate idFilter = new Predicate("id", Operator.IN, Collections.singletonList(idVal));
            filters = Collections.singleton(idFilter);
        }

        /* getRelation performs read permission checks */
        Set<PersistentResource> resources = getRelation(relation, filters);
        for (PersistentResource childResource : resources) {
            if (childResource.matchesId(id)) {
                return childResource;
            }
        }
        throw new InvalidObjectIdentifierException(id, relation);
    }