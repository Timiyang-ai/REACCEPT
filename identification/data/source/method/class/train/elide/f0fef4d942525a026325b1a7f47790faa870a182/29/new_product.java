protected Set<PersistentResource> getRelation(String relationName, Set<Predicate> filters) {
        List<String> relations = dictionary.getRelationships(obj);

        String realName = dictionary.getNameFromAlias(obj, relationName);
        relationName = (realName == null) ? relationName : realName;

        if (relationName == null || relations == null || !relations.contains(relationName)) {
            throw new InvalidAttributeException("No attribute '" + relationName + "' in " + type);
        }

        Set<PersistentResource<Object>> resources = Sets.newLinkedHashSet();

        RelationshipType type = getRelationshipType(relationName);
        Object val = this.getValue(relationName);
        if (val == null) {
            return (Set) resources;
        } else if (isDenyFilter(requestScope, dictionary.getParameterizedType(obj, relationName))) {
            return (Set) resources;
        } else if (val instanceof Collection) {
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