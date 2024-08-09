public CriteriaQuery isBlank(String propName) {
        criterion = criterion.and(criterionBuilder.isBlank(propName));
        return this;
    }