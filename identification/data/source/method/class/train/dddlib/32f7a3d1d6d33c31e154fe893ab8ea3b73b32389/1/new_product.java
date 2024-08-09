public CriteriaQuery notEmpty(String propName) {
        criterion = criterion.and(criterionBuilder.notEmpty(propName));
        return this;
    }