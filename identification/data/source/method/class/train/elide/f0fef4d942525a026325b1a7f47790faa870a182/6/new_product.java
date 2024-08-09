public Set<PersistentResource> getRelation(String relationName) {
        if (requestScope.getTransaction() != null && !requestScope.getPredicates().isEmpty()) {
            final Class<?> entityClass = dictionary.getParameterizedType(obj, relationName);
            final String valType = dictionary.getBinding(entityClass);
            final Set<Predicate> filters = new HashSet<>(requestScope.getPredicatesOfType(valType));
            return getRelation(relationName, filters);
        } else {
            return getRelation(relationName, Collections.<Predicate>emptySet());
        }
    }