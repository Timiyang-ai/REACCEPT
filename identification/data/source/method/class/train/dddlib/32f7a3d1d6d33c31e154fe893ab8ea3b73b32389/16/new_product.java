public CriteriaQuery notIn(String propName, Collection<? extends Object> value) {
        criterion = criterion.and(criterionBuilder.notIn(propName, value));
        return this;
    }