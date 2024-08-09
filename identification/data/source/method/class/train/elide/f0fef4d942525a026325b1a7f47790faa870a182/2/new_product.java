public Set<PersistentResource> getRelation(String relationName) {
        // if pagination, then sorting with filters...
        boolean hasFilters = !requestScope.getPredicates().isEmpty();
        Set<Predicate> filters = null;
        if (requestScope.getTransaction() != null && requestScope.getQueryParams().isPresent()) {
            if (hasFilters) {
                final Class<?> entityClass = dictionary.getParameterizedType(obj, relationName);
                final String valType = dictionary.getBinding(entityClass);
                filters = new HashSet<>(requestScope.getPredicatesOfType(valType));
            }
            return getRelation(relationName, Optional.ofNullable(filters), Optional.of(requestScope.getSorting()),
                    Optional.of(requestScope.getPagination()));
            //return getRelation(relationName, filters);
        } else {
            return getRelation(relationName, Collections.<Predicate>emptySet());
        }
    }