public Set<PersistentResource> getRelation(String relation,
                                          List<String> ids,
                                          Optional<FilterExpression> filter,
                                          Optional<Sorting> sorting,
                                          Optional<Pagination> pagination) {

        FilterExpression filterExpression;

        Class<?> entityType = dictionary.getParameterizedType(getResourceClass(), relation);
        String typeAlias = (entityType == null) ? null : dictionary.getJsonAliasFor(entityType);

        Set<PersistentResource> resources = new LinkedHashSet<>();

        /* If this is a bulk edit request and the ID we are fetching for is newly created... */
        if (entityType == null) {
            throw new InvalidAttributeException(relation, type);
        } else {
            Class<?> idType = dictionary.getIdType(entityType);
            String idField = dictionary.getIdFieldName(entityType);

            if (!ids.isEmpty()) {
                Map<String, PersistentResource> newResources = requestScope.getNewPersistentResources().stream()
                        .filter(r -> typeAlias.equals(r.getType()))
                        .collect(Collectors.toMap(r -> (String) r.getUUID().orElse(""), r -> r));
                // Fetch our set of new resources that we know about since we can't find them in the datastore
                resources = new LinkedHashSet<>(ids.stream()
                        .filter(newResources::containsKey)
                        .map(newResources::get)
                        .collect(Collectors.toSet()));
                FilterExpression idExpression = new FilterPredicate(
                        new FilterPredicate.PathElement(
                                entityType,
                                idType,
                                idField),
                        Operator.IN,
                        ids.stream()
                                // Filter out new ids from our expression-- these don't exist in the datastore yet
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

        // TODO: Filter on new resources?
        // TODO: Update pagination to subtract the number of new resources created?

        resources.addAll(filter(ReadPermission.class,
            (Set) getRelationChecked(relation, Optional.ofNullable(filterExpression), sorting, pagination)));

        // TODO: Sort again in memory now that two sets are glommed together?

        Set<String> added = new HashSet<>();
        for (PersistentResource childResource : resources) {
            for (String id : ids) {
                if (childResource.matchesId(id)) {
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

        return resources;
    }