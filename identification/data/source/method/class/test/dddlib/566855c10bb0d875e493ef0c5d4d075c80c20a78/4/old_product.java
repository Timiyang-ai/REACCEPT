public CriteriaQuery le(String propName, Comparable<?> value) {
        criterion = criterion.and(criterionBuilder.le(propName, value));
        return this;
    }