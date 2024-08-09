public Set<PersistentResource> getRelation(String relation,
                                          List<String> ids,
                                          Optional<FilterExpression> filter,
                                          Optional<Sorting> sorting,
                                          Optional<Pagination> pagination) {

        FilterExpression filterExpression = null;
        boolean skipNew = false;

        Class<?> entityType = dictionary.getParameterizedType(getResourceClass(), relation);

        Set<PersistentResource> relations = new LinkedHashSet<>();

        /* If this is a bulk edit request and the ID we are fetching for is newly created... */
        if (entityType == null) {
            throw new InvalidAttributeException(relation, type);
        } else {
            Class<?> idType = dictionary.getIdType(entityType);
            String idField = dictionary.getIdFieldName(entityType);

            if (!ids.isEmpty()) {
                FilterExpression idExpression = new FilterPredicate(
                        new FilterPredicate.PathElement(
                                entityType,
                                idType,
                                idField),
                        Operator.IN,
                        ids.stream()
                                // Filter new ids
                                .filter(id ->
                                        requestScope.getObjectById(dictionary.getJsonAliasFor(entityType), id) == null)
                                .map(id -> CoerceUtil.coerce(id, idType)).collect(Collectors.toList()));
                // Combine filters if necessary
                filterExpression = filter
                        .map(fe -> (FilterExpression) new AndFilterExpression(idExpression, fe))
                        .orElse(idExpression);
            } else {
                filterExpression = filter.orElse(null);
            }
        }

        Set<PersistentResource> resources =
                filter(ReadPermission.class,
                        (Set) getRelationChecked(relation, Optional.ofNullable(filterExpression), sorting, pagination));

        Set<String> added = new HashSet<>();
        for (PersistentResource childResource : resources) {
            for (String id : ids) {
                if (childResource.matchesId(id)) {
                    relations.add(childResource);
                    added.add(id);
                    break;
                }
            }
        }

        String missedIds = ids.stream()
                .filter(id -> !added.contains(id))
                .reduce(new StringBuilder(),
                        (prev, curr) -> (prev.length() > 0) ? prev.append(curr).append(",") : prev.append(curr),
                        StringBuilder::append)
                .toString();

        if (!missedIds.isEmpty()) {
            throw new InvalidObjectIdentifierException(missedIds, relation);
        }

        return relations;
    }