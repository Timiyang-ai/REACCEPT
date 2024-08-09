public CriteriaQuery isBlank(String propName) {
        addCriterion(criterionBuilder.isBlank(propName));
        return this;
    }