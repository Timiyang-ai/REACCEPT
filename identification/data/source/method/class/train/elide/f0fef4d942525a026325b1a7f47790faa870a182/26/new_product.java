public PersistentResource getRelation(String relation, String id) {
        Set<Predicate> filters;
        // Filtering not supported in Patxh extension
        if (requestScope instanceof PatchRequestScope) {
            filters = Collections.emptySet();
        } else {
            Class<?> entityType = dictionary.getParameterizedType(getResourceClass(), relation);
            if (entityType == null) {
                throw new InvalidAttributeException(relation, type);
            }
            Object idVal = CoerceUtil.coerce(id, dictionary.getIdType(entityType));
            String idField = dictionary.getIdFieldName(entityType);
            Predicate idFilter = new Predicate(idField, Operator.IN, Collections.singletonList(idVal));
            filters = Collections.singleton(idFilter);
        }

        Set<PersistentResource> resources = filter(ReadPermission.class, (Set) getRelationChecked(relation, filters));
        for (PersistentResource childResource : resources) {
            if (childResource.matchesId(id)) {
                return childResource;
            }
        }
        throw new InvalidObjectIdentifierException(id, relation);
    }