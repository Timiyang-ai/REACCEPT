public CriteriaQuery isNull(String propName) {
        addCriterion(criterionBuilder.isNull(propName));
        return this;
    }