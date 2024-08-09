public PersistentResource getRelation(String relation, String id) {

        Optional<FilterExpression> filterExpression;
        boolean skipNew = false;
        // Criteria filtering not supported in Patch extension
        if (requestScope instanceof PatchRequestScope) {
            filterExpression = Optional.empty();
            // NOTE: We can safely _skip_ tests here since we are only skipping READ checks on
            // NEWLY created objects. We assume a user can READ their object in the midst of creation.
            // Imposing a constraint to the contrary-- at this moment-- seems arbitrary and does not
            // reflect reality (i.e. if a user is creating an object with values, he/she knows those values
            // already).
            skipNew = true;
        } else {
            Class<?> entityType = dictionary.getParameterizedType(getResourceClass(), relation);
            if (entityType == null) {
                throw new InvalidAttributeException(relation, type);
            }
            Class<?> idType = dictionary.getIdType(entityType);
            Object idVal = CoerceUtil.coerce(id, idType);
            String idField = dictionary.getIdFieldName(entityType);

            List<Predicate.PathElement> path = Lists.newArrayList(
                new Predicate.PathElement(
                    getResourceClass(),
                    getType(),
                    entityType,
                    relation
                ),
                new Predicate.PathElement(
                    entityType,
                    relation,
                    idType,
                    idField
                )
            );

            filterExpression = Optional.of(new Predicate(
                    path,
                    Operator.IN,
                    Collections.singletonList(idVal)));
        }

        Set<PersistentResource> resources =
                filter(ReadPermission.class,
                (Set) getRelationChecked(relation, filterExpression),
                skipNew);

        for (PersistentResource childResource : resources) {
            if (childResource.matchesId(id)) {
                return childResource;
            }
        }
        throw new InvalidObjectIdentifierException(id, relation);
    }