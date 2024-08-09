public CriteriaQuery isEmpty(String propName) {
        addCriterion(criterionBuilder.isEmpty(propName));
        return this;
    }