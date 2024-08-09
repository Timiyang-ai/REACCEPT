public CriteriaQuery isEmpty(String propName) {
        criterion = criterion.and(criterionBuilder.isEmpty(propName));
        return this;
    }