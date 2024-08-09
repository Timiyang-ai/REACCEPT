public CriteriaQuery in(String propName, Collection<? extends Object> value) {
        criterion = criterion.and(criterionBuilder.in(propName, value));
        return this;
    }