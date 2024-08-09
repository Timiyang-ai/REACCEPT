public CriteriaQuery notBlank(String propName) {
        criterion = criterion.and(criterionBuilder.notBlank(propName));
        return this;
    }