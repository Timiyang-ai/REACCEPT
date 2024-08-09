public PersistentResource getRelation(String relation, String id) {

        Optional<FilterExpression> filterExpression;
        boolean skipNew = false;

        Class<?> entityType = dictionary.getParameterizedType(getResourceClass(), relation);

        /* If this is a bulk edit request and the ID we are fetching for is newly created... */
        if (entityType == null) {
            throw new InvalidAttributeException(relation, type);
        } else if (requestScope.isMutatingMultipleEntities()
                && requestScope.getObjectById(dictionary.getJsonAliasFor(entityType), id) != null) {
            filterExpression = Optional.empty();
            // NOTE: We can safely _skip_ tests here since we are only skipping READ checks on
            // NEWLY created objects. We assume a user can READ their object in the midst of creation.
            // Imposing a constraint to the contrary-- at this moment-- seems arbitrary and does not
            // reflect reality (i.e. if a user is creating an object with values, he/she knows those values
            // already).
            skipNew = true;
        } else {
            Class<?> idType = dictionary.getIdType(entityType);
            Object idVal = CoerceUtil.coerce(id, idType);
            String idField = dictionary.getIdFieldName(entityType);

            filterExpression = Optional.of(new FilterPredicate(
                    new FilterPredicate.PathElement(
                            entityType,
                            idType,
                            idField),
                    Operator.IN,
                    Collections.singletonList(idVal)));
        }

        Set<PersistentResource> resources =
                filter(ReadPermission.class, getRelationChecked(relation, filterExpression), skipNew);

        for (PersistentResource childResource : resources) {
            if (childResource.matchesId(id)) {
                return childResource;
            }
        }
        throw new InvalidObjectIdentifierException(id, relation);
    }