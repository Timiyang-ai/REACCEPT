public CriteriaQuery isTrue(String propName) {
        criterion = criterion.and(criterionBuilder.isTrue(propName));
        return this;
    }