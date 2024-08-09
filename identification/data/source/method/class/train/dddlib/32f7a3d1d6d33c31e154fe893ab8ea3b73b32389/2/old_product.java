public CriteriaQuery in(String propName, Collection<? extends Object> value) {
        addCriterion(criterionBuilder.in(propName, value));
        return this;
    }