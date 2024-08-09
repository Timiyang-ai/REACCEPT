public CriteriaQuery lt(String propName, Comparable<?> value) {
        criterion = criterion.and(criterionBuilder.lt(propName, value));
        return this;
    }