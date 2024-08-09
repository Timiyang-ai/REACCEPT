public CriteriaQuery gt(String propName, Comparable<?> value) {
        criterion = criterion.and(criterionBuilder.gt(propName, value));
        return this;
    }