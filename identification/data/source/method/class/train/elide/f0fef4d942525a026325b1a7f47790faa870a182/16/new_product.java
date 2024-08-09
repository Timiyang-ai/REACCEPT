public Set<PersistentResource> getRelation(List<String> ids, com.yahoo.elide.request.Relationship relationship) {

        FilterExpression filterExpression = Optional.ofNullable(relationship.getProjection().getFilterExpression())
                .orElse(null);

        assertRelationshipExists(relationship.getName());
        Class<?> entityType = dictionary.getParameterizedType(getResourceClass(), relationship.getName());

        Set<PersistentResource> newResources = new LinkedHashSet<>();

        /* If this is a bulk edit request and the ID we are fetching for is newly created... */
        if (!ids.isEmpty()) {
            // Fetch our set of new resources that we know about since we can't find them in the datastore
            newResources = requestScope.getNewPersistentResources().stream()
                    .filter(resource -> entityType.isAssignableFrom(resource.getResourceClass())
                            && ids.contains(resource.getUUID().orElse("")))
                    .collect(Collectors.toSet());

            FilterExpression idExpression = buildIdFilterExpression(ids, entityType, dictionary, requestScope);

            // Combine filters if necessary
            filterExpression = Optional.ofNullable(relationship.getProjection().getFilterExpression())
                    .map(fe -> (FilterExpression) new AndFilterExpression(idExpression, fe))
                    .orElse(idExpression);
        }

        // TODO: Filter on new resources?
        // TODO: Update pagination to subtract the number of new resources created?

        Set<PersistentResource> existingResources = filter(ReadPermission.class,

        getRelation(relationship.copyOf()
            .projection(relationship.getProjection().copyOf()
                        .filterExpression(filterExpression)
                        .build())
                    .build(), true));

        // TODO: Sort again in memory now that two sets are glommed together?

        Set<PersistentResource> allResources = Sets.union(newResources, existingResources);
        Set<String> allExpectedIds = allResources.stream()
                .map(resource -> (String) resource.getUUID().orElseGet(resource::getId))
                .collect(Collectors.toSet());
        Set<String> missedIds = Sets.difference(new HashSet<>(ids), allExpectedIds);

        if (!missedIds.isEmpty()) {
            throw new InvalidObjectIdentifierException(missedIds.toString(), relationship.getName());
        }

        return allResources;
    }