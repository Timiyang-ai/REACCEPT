public Set<PersistentResource> getRelation(String relation,
                                          List<String> ids,
                                          Optional<FilterExpression> filter,
                                          Optional<Sorting> sorting,
                                          Optional<Pagination> pagination) {

        FilterExpression filterExpression;

        Class<?> entityType = dictionary.getParameterizedType(getResourceClass(), relation);

        Set<PersistentResource> newResources = new LinkedHashSet<>();

        /* If this is a bulk edit request and the ID we are fetching for is newly created... */
        if (entityType == null) {
            throw new InvalidAttributeException(relation, type);
        } else {
            if (!ids.isEmpty()) {
                String typeAlias = dictionary.getJsonAliasFor(entityType);
                Map<String, PersistentResource> newResourceMap = requestScope.getNewPersistentResources().stream()
                        .filter(r -> typeAlias.equals(r.getType()))
                        .collect(Collectors.toMap(r -> (String) r.getUUID().orElse(""), r -> r));

                // Fetch our set of new resources that we know about since we can't find them in the datastore
                newResources = new LinkedHashSet<>(ids.stream()
                        .filter(newResourceMap::containsKey)
                        .map(newResourceMap::get)
                        .collect(Collectors.toList()));

                FilterExpression idExpression = buildIdFilterExpression(ids, entityType);

                // Combine filters if necessary
                filterExpression = filter
                        .map(fe -> joinFilterExpressions(idExpression, fe))
                        .orElse(idExpression);
            } else {
                filterExpression = filter.orElse(null);
            }
        }

        // TODO: Filter on new resources?
        // TODO: Update pagination to subtract the number of new resources created?

        Set<PersistentResource> existingResources = filter(ReadPermission.class,
            (Set) getRelationChecked(relation, Optional.ofNullable(filterExpression), sorting, pagination));

        // TODO: Sort again in memory now that two sets are glommed together?

        Set<PersistentResource> allResources = Sets.union(newResources, existingResources);

        String missedIds = ids.stream()
                .filter(id -> allResources.stream().noneMatch(p -> p.matchesId(id)))
                .reduce(new StringBuilder(),
                        (prev, curr) -> (prev.length() > 0) ? prev.append(curr).append(",") : prev.append(curr),
                        StringBuilder::append)
                .toString();

        if (!missedIds.isEmpty()) {
            throw new InvalidObjectIdentifierException(missedIds, relation);
        }

        return allResources;
    }