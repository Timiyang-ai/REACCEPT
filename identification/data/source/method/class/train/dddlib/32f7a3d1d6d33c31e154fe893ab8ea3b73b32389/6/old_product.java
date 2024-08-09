public CriteriaQuery isTrue(String propName) {
        addCriterion(criterionBuilder.isTrue(propName));
        return this;
    }