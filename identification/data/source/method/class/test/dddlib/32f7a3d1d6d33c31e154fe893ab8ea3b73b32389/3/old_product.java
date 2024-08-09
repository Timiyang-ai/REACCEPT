public CriteriaQuery notNull(String propName) {
        addCriterion(criterionBuilder.notNull(propName));
        return this;
    }