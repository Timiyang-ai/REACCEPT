public CriteriaQuery isFalse(String propName) {
        criterion = criterion.and(criterionBuilder.isFalse(propName));
        return this;
    }