public CriteriaQuery lt(String propName, Comparable<?> value) {
        criterion = criterion.and(Criteria.lt(propName, value));
        return this;
    }