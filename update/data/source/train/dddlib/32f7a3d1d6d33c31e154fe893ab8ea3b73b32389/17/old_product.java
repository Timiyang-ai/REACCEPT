public CriteriaQuery notBlank(String propName) {
        addCriterion(criterionBuilder.notBlank(propName));
        return this;
    }