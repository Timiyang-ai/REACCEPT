public CriteriaQuery notIn(String propName, Collection<? extends Object> value) {
        addCriterion(criterionBuilder.notIn(propName, value));
        return this;
    }