public CriteriaQuery isFalse(String propName) {
        addCriterion(criterionBuilder.isFalse(propName));
        return this;
    }