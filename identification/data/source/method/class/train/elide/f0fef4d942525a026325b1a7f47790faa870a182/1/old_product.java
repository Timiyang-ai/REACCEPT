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
                // Fetch our set of new resources that we know about since we can't find them in the datastore
                String typeAlias = dictionary.getJsonAliasFor(entityType);
                newResources = requestScope.getNewPersistentResources().stream()
                        .filter(resource -> typeAlias.equals(resource.getType())
                                && ids.contains(resource.getUUID().orElse("")))
                        .collect(Collectors.toSet());

                FilterExpression idExpression = buildIdFilterExpression(ids, entityType);

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

        Set<PersistentResource> existingResources = filter(ReadPermission.class,
            (Set) getRelationChecked(relation, Optional.ofNullable(filterExpression), sorting, pagination));

        // TODO: Sort again in memory now that two sets are glommed together?

        Set<PersistentResource> allResources = Sets.union(newResources, existingResources);
        Set<String> allExpectedIds = allResources.stream()
                .map(resource -> (String) resource.getUUID().orElseGet(resource::getId))
                .collect(Collectors.toSet());
        Set<String> missedIds = Sets.difference(new HashSet<>(ids), allExpectedIds);

        if (!missedIds.isEmpty()) {
            throw new InvalidObjectIdentifierException(missedIds.toString(), relation);
        }

        return allResources;
    }