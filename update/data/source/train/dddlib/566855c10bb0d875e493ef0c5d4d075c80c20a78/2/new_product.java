public CriteriaQuery gt(String propName, Comparable<?> value) {
        criterion = criterion.and(Criteria.gt(propName, value));
        return this;
    }