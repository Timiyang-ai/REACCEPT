public CriteriaQuery isNull(String propName) {
        criterion = criterion.and(criterionBuilder.isNull(propName));
        return this;
    }