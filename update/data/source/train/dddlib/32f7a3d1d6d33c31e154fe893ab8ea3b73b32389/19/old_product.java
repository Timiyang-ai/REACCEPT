public CriteriaQuery notEmpty(String propName) {
        addCriterion(criterionBuilder.notEmpty(propName));
        return this;
    }