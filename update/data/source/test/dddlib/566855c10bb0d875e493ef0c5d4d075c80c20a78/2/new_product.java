public CriteriaQuery le(String propName, Comparable<?> value) {
        criterion = criterion.and(Criteria.le(propName, value));
        return this;
    }