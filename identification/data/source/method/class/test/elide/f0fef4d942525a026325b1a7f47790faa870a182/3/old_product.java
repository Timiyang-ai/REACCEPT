protected Set<PersistentResource> getRelation(String relationName, Set<Predicate> filters) {
        List<String> relations = dictionary.getRelationships(obj);

        String realName = dictionary.getNameFromAlias(obj, relationName);
        relationName = (realName == null) ? relationName : realName;

        if (relationName == null || relations == null || !relations.contains(relationName)) {
            throw new InvalidAttributeException(relationName, type);
        }


        // check for deny access on relationship to avoid iterating a lazy collection
        checkFieldAwarePermissions(ReadPermission.class, relationName, null, null);
        try {
            // If we cannot read any element of this type, don't try to filter
            requestScope.getPermissionExecutor().checkUserPermissions(
                    dictionary.getParameterizedType(obj, relationName),
                    ReadPermission.class);
        } catch (ForbiddenAccessException e) {
            return Collections.emptySet();
        }

        RelationshipType type = getRelationshipType(relationName);
        Object val = this.getValue(relationName);
        if (val == null) {
            return Collections.emptySet();
        } else if (shouldSkipCollection(ReadPermission.class, relationName)) {
            return Collections.emptySet();
        }

        Set<PersistentResource<Object>> resources = Sets.newLinkedHashSet();
        if (val instanceof Collection) {
            Collection filteredVal = (Collection) val;

            if (!filters.isEmpty()) {
                final Class<?> entityClass = dictionary.getParameterizedType(obj, relationName);
                filteredVal = requestScope.getTransaction().filterCollection(filteredVal, entityClass, filters);
            }

            resources = new PersistentResourceSet(filteredVal, requestScope);
        } else if (type.isToOne()) {
            resources = new SingleElementSet(new PersistentResource(this, val, getRequestScope()));
        } else {
            resources.add(new PersistentResource(this, val, getRequestScope()));
        }

        return (Set) filter(ReadPermission.class, resources);
    }